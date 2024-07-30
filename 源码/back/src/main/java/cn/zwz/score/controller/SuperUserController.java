package cn.zwz.score.controller;

import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.service.IUserService;
import cn.zwz.score.entity.ScoreData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "用户管理接口")
@RequestMapping("/zwz/superUser")
@Transactional
public class SuperUserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户数据")
    public Result<List<User>> getAll(@RequestParam Integer type){
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("type",type);
        return new ResultUtil<List<User>>().setData(iUserService.list(qw));
    }
}
