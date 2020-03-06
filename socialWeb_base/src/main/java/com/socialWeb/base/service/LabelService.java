package com.socialWeb.base.service;

import com.socialWeb.base.dao.LabelDao;
import com.socialWeb.base.pojo.Label;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

/**
 * @ClassName LabelService
 * @Description TODO
 * @Author 42
 * @Date 2020/3/6 下午 5:26
 * @Version 1.0
 */
@Service
@Transactional
public class LabelService
{
	@Autowired
	private LabelDao labelDao;
	@Autowired
	private IdWorker idWorker;

	public List<Label> findAll() {
	    return labelDao.findAll();
	}

	public Label findById(String id){
		return labelDao.findById(id).get();
	}

	public void save(Label label){
		label.setId(idWorker.nextId()+"");
		labelDao.save(label);
	}

	public void update(Label label){
		labelDao.save(label);
	}

	public void deleteById(String id){
		labelDao.deleteById(id);
	}
}
