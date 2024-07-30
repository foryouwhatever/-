package cn.zwz.score.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.score.entity.ScoreProject;
import cn.zwz.score.service.IScoreProjectService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Slf4j
@RestController
@Api(tags = "评分项目管理接口")
@RequestMapping("/zwz/scoreProject")
@Transactional
public class ScoreProjectController {

    @Autowired
    private IScoreProjectService iScoreProjectService;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条评分项目")
    public Result<ScoreProject> get(@RequestParam String id){
        return new ResultUtil<ScoreProject>().setData(iScoreProjectService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部评分项目个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iScoreProjectService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部评分项目")
    public Result<List<ScoreProject>> getAll(){
        QueryWrapper<ScoreProject> qw = new QueryWrapper<>();
        qw.orderByAsc("sort_order");
        List<ScoreProject> projectList = iScoreProjectService.list(qw);
        return new ResultUtil<List<ScoreProject>>().setData(projectList);
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询评分项目")
    public Result<IPage<ScoreProject>> getByPage(@ModelAttribute ScoreProject scoreProject ,@ModelAttribute PageVo page){
        QueryWrapper<ScoreProject> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(scoreProject.getTitle())) {
            qw.like("title",scoreProject.getTitle());
        }
        if(!ZwzNullUtils.isNull(scoreProject.getContent())) {
            qw.like("content",scoreProject.getContent());
        }
        qw.orderByAsc("sort_order");
        IPage<ScoreProject> data = iScoreProjectService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<ScoreProject>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改评分项目")
    public Result<ScoreProject> saveOrUpdate(ScoreProject scoreProject){
        if(iScoreProjectService.saveOrUpdate(scoreProject)){
            return new ResultUtil<ScoreProject>().setData(scoreProject);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增评分项目")
    public Result<ScoreProject> insert(ScoreProject scoreProject){
        if(Objects.equals(0,scoreProject.getSortOrder().compareTo(BigDecimal.ZERO))) {
            scoreProject.setSortOrder(BigDecimal.valueOf(iScoreProjectService.count()));
        }
        iScoreProjectService.saveOrUpdate(scoreProject);
        return new ResultUtil<ScoreProject>().setData(scoreProject);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑评分项目")
    public Result<ScoreProject> update(ScoreProject scoreProject){
        iScoreProjectService.saveOrUpdate(scoreProject);
        return new ResultUtil<ScoreProject>().setData(scoreProject);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除评分项目")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iScoreProjectService.removeById(id);
        }
        return ResultUtil.success();
    }
}
