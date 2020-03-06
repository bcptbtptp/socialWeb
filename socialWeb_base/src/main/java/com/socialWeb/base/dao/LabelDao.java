package com.socialWeb.base.dao;

import com.socialWeb.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @InterfaceName LabelDao
 * @Description TODO
 * @Author 42
 * @Date 2020/3/6 下午 5:25
 * @Version 1.0
 */

public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label>
{

}
