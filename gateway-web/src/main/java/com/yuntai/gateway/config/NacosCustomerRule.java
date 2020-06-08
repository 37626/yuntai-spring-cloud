package com.yuntai.gateway.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.ExtendBalancer;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author gourd.hu
 */
@Slf4j
public class NacosCustomerRule extends AbstractLoadBalancerRule {

 @Autowired
 private NacosDiscoveryProperties nacosDiscoveryProperties;

 @Override
 public Server choose(Object key) {
  try {
   String clusterName = this.nacosDiscoveryProperties.getClusterName();
   DynamicServerListLoadBalancer loadBalancer = (DynamicServerListLoadBalancer)this.getLoadBalancer();
   String name = loadBalancer.getName();
   NamingService namingService = this.nacosDiscoveryProperties.namingServiceInstance();
   List<Instance> instances = namingService.selectInstances(name,nacosDiscoveryProperties.getGroup(), true);
   if (CollectionUtils.isEmpty(instances)) {
    log.warn("no instance in service {}", name);
    return null;
   } else {
    List<Instance> instancesToChoose = instances;
    if (StringUtils.isNotBlank(clusterName)) {
     List<Instance> sameClusterInstances = (List)instances.stream().filter((instancex) -> {
      return Objects.equals(clusterName, instancex.getClusterName());
     }).collect(Collectors.toList());
     if (!CollectionUtils.isEmpty(sameClusterInstances)) {
      instancesToChoose = sameClusterInstances;
     } else {
      log.warn("A cross-cluster call occurs，name = {}, clusterName = {}, instance = {}", new Object[]{name, clusterName, instances});
     }
    }

    Instance instance = ExtendBalancer.getHostByRandomWeight2(instancesToChoose);
    return new NacosServer(instance);
   }
  } catch (Exception var9) {
   log.warn("NacosRule error", var9);
   return null;
  }
 }

 @Override
 public void initWithNiwsConfig(IClientConfig iClientConfig) {
 }

}