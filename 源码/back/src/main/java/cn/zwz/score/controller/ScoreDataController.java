package cn.zwz.score.controller;

import cn.hutool.core.date.DateUtil;
import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.service.IUserService;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.score.entity.ScoreData;
import cn.zwz.score.entity.ScoreProject;
import cn.zwz.score.service.IScoreDataService;
import cn.hutool.core.util.StrUtil;
import cn.zwz.score.service.IScoreProjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Slf4j
@RestController
@Api(tags = "评分数据管理接口")
@RequestMapping("/zwz/scoreData")
@Transactional
public class ScoreDataController {

    @Autowired
    private IScoreDataService iScoreDataService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IScoreProjectService iScoreProjectService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条评分数据")
    public Result<ScoreData> get(@RequestParam String id){
        return new ResultUtil<ScoreData>().setData(iScoreDataService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部评分数据个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iScoreDataService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部评分数据")
    public Result<List<ScoreData>> getAll(){
        return new ResultUtil<List<ScoreData>>().setData(iScoreDataService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询评分数据")
    public Result<IPage<ScoreData>> getByPage(@ModelAttribute ScoreData scoreData ,@ModelAttribute PageVo page){
        QueryWrapper<ScoreData> qw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        QueryWrapper<User> userQw = new QueryWrapper<>();
        userQw.eq("id",currUser.getId());
        userQw.inSql("id","SELECT DISTINCT user_id FROM a_user_role WHERE del_flag = 0 AND (role_id = '1536606659751841799' OR role_id = '1674219991056125952')");
        if(iUserService.count(userQw) > 0L) {
            qw.eq("student_id",currUser.getId());
        }
        if(!ZwzNullUtils.isNull(scoreData.getProjectName())) {
            qw.like("project_name",scoreData.getProjectName());
        }
        if(!ZwzNullUtils.isNull(scoreData.getStudentName())) {
            qw.like("student_name",scoreData.getStudentName());
        }
        if(!ZwzNullUtils.isNull(scoreData.getWorkName())) {
            qw.like("work_name",scoreData.getWorkName());
        }
        IPage<ScoreData> data = iScoreDataService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<ScoreData>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改评分数据")
    public Result<ScoreData> saveOrUpdate(ScoreData scoreData){
        if(iScoreDataService.saveOrUpdate(scoreData)){
            return new ResultUtil<ScoreData>().setData(scoreData);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增评分数据")
    public Result<ScoreData> insert(ScoreData scoreData){
        ScoreProject sp = iScoreProjectService.getById(scoreData.getProjectId());
        if(sp == null) {
            return ResultUtil.error("评分项目不存在");
        }
        scoreData.setProjectName(sp.getTitle());
        User student = iUserService.getById(scoreData.getStudentId());
        if(student == null) {
            return ResultUtil.error("学生不存在");
        }
        scoreData.setStudentName(student.getNickname());
        User currUser = securityUtil.getCurrUser();
        scoreData.setWorkId(currUser.getId());
        scoreData.setWorkName(currUser.getNickname());
        scoreData.setTime(DateUtil.now());
        iScoreDataService.saveOrUpdate(scoreData);
        return new ResultUtil<ScoreData>().setData(scoreData);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑评分数据")
    public Result<ScoreData> update(ScoreData scoreData){
        ScoreProject sp = iScoreProjectService.getById(scoreData.getProjectId());
        if(sp == null) {
            return ResultUtil.error("评分项目不存在");
        }
        scoreData.setProjectName(sp.getTitle());
        User student = iUserService.getById(scoreData.getStudentId());
        if(student == null) {
            return ResultUtil.error("学生不存在");
        }
        scoreData.setStudentName(student.getNickname());
        iScoreDataService.saveOrUpdate(scoreData);
        return new ResultUtil<ScoreData>().setData(scoreData);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除评分数据")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iScoreDataService.removeById(id);
        }
        return ResultUtil.success();
    }
}
