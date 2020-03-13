package com.socialWeb.base.service;

import com.socialWeb.base.dao.LabelDao;
import com.socialWeb.base.pojo.Label;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

	public List<Label> findSearch(Label label) {
		return labelDao.findAll(new Specification<Label>(){
			/**
			 *
			 * @param root 根对象，把条件封装到哪个对象，where 类名 = label.getId
			 * @param criteriaQuery 查询关键字
			 * @param criteriaBuilder 封装条件对象
			 * @return
			 */
			@Override
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List <Predicate> predicates = new ArrayList<>();
				if (null != label.getLabelname() && !"".equals(label.getLabelname())){
					Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%"+label.getLabelname()+"%");
					predicates.add(predicate);
				}
				if (null != label.getState() && !"".equals(label.getState())){
					Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
					predicates.add(predicate);
				}
				Predicate[] result = new Predicate[predicates.size()];
				predicates.toArray(result);
				return criteriaBuilder.and(result);
			}
		});
	}

	public Page<Label> pageQuery(Label label, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return labelDao.findAll(new Specification<Label>(){
			@Override
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List <Predicate> predicates = new ArrayList<>();
				if (null != label.getLabelname() && !"".equals(label.getLabelname())){
					Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%"+label.getLabelname()+"%");
					predicates.add(predicate);
				}
				if (null != label.getState() && !"".equals(label.getState())){
					Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
					predicates.add(predicate);
				}
				Predicate[] result = new Predicate[predicates.size()];
				predicates.toArray(result);
				return criteriaBuilder.and(result);
			}
		}, pageable);
	}
}
