package com.yuntai.web.domain.bo;

import com.yuntai.web.domain.entity.YtPermsRole;
import lombok.Data;

import java.util.List;
@Data
public class PermsRoleBo extends YtPermsRole {
    public List<String> roleIds;
}
