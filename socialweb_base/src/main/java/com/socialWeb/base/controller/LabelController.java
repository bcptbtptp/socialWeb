package com.socialWeb.base.controller;
import com.socialWeb.base.pojo.Label;
import com.socialWeb.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LabelController
 * @Description TODO
 * @Author 42
 * @Date 2020/3/6 下午 4:21
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
@RefreshScope
public class LabelController
{
	@Autowired
	private LabelService labelService;
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
	}

	@RequestMapping(value="/{labelId}",method=RequestMethod.GET)
	public Result findById(@PathVariable("labelId") String labelId){
		return new Result(true,StatusCode.OK,"查询成功",labelService.findById(labelId));
	}

	@RequestMapping(method=RequestMethod.POST)
	public Result save(@RequestBody Label label){
		labelService.save(label);
		return new Result(true,StatusCode.OK,"添加成功");
	}

	@RequestMapping(value="/{labelId}",method=RequestMethod.PUT)
	public Result update(@PathVariable("labelId") String labelId,@RequestBody Label label){
		label.setId(labelId);
		labelService.update(label);
		return new Result(true,StatusCode.OK,"更新成功");
	}

	@RequestMapping(value="/{labelId}",method=RequestMethod.DELETE)
	public Result deleteById(@PathVariable("labelId") String labelId){
		labelService.deleteById(labelId);
		return new Result(true,StatusCode.OK,"删除成功");
	}

	@RequestMapping(value="/search",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Label label){
		List<Label> list = labelService.findSearch(label);
		return new Result(true,StatusCode.OK,"查询成功", list);
	}

	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size){
		Page<Label> pageData = labelService.pageQuery(label, page, size);
		return new Result(true,StatusCode.OK,"查询成功", new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
	}
}