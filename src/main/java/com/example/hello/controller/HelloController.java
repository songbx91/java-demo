package com.example.hello.controller;

import com.example.hello.helper.FriendshipHelper;
import com.example.hello.model.*;
import com.example.hello.model.dynamo.UserV2;
import com.example.hello.model.type.GenderEnum;
import com.example.hello.repository.HelloRepository;
import com.example.hello.repository.UserV2Repository;
import com.example.hello.request.FriendApply;
import com.example.hello.resource.ResultVO;
import com.example.hello.service.IBlacklistService;
import com.example.hello.service.IFriendApplyService;
import com.example.hello.service.IFriendshipService;
import com.example.hello.utils.GeneralUtils;
import com.example.hello.utils.JsonUtils;
import com.example.hello.utils.JwtUtils;
import com.example.hello.utils.ResultVOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private HelloRepository helloRepository;  //实例化接口

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private IFriendApplyService friendApplyService;

    @Autowired
    private IFriendshipService friendshipService;

    @Autowired
    private IBlacklistService blacklistService;

    @Autowired
    private FriendshipHelper friendshipHelper;

    @Autowired
    private UserV2Repository userV2Repository;

    @PostMapping("/user-create")
    public void userCreate(@RequestParam("id") String id,
                           @RequestParam("first_name") String firstName,
                           @RequestParam("last_name") String lastName) {
        UserV2 user = new UserV2();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userV2Repository.save(user);
    }

    @PostMapping("/api-test")
    public void test() {
        System.out.println("test");
    }

    @PostMapping("/get-token")
    public ResultVO<Object> getToken() throws IOException {
        String token = JwtUtils.createJWT(String.valueOf(new Date()), "user1", 60000L);
        return ResultVOUtil.success(token);
    }

    @PostMapping("/jwt-visit")
    public ResultVO<Object> jwtVisit() {
        System.out.println("访问中。。。");
        return ResultVOUtil.success("访问成功");
    }

    @GetMapping("/json-test")
    public List<Integer> jsonTest() {
        String str = "[]";
        Optional<List> listOptional = JsonUtils.jsonToObject(str, List.class);
        List<Integer> lst = new ArrayList<Integer>();
        if (!listOptional.isPresent()) {
            return lst;
        }
        return listOptional.get();
    }

//    @PostMapping("/circle-create")
//    public void createCircle(@RequestParam("x") Integer x, @RequestParam("y") Integer y, @RequestParam("r") Integer r) {
//        final Circle circle = new Circle();
//        circle.setPosition(x, y);
//        circle.setRadius(r);
//        circle.setColor("red");
//        circle.setId("123456");
//        circle.setLastUpdateTime(DateTime.now());
//
//        circlesRepository.save(circle);
//    }

    @GetMapping("/pipeline-test")
    public void pipelineTest(@RequestParam("uid1") Integer uid1, @RequestParam("uid2") Integer uid2) {
//        if (friendApplyService.valid(uid1.longValue(), uid2.longValue())) {
//            logger.info("好友关系存在");
//        } else {
//            logger.info("好友关系不存在");
//        }
        FriendApply friendApply = new FriendApply();
        friendApply.setUserId(uid1);
        friendApply.setTargetUserId(uid2);
        friendshipHelper.apply(friendApply);
//        boolean exists = blacklistService.blocked(uid1, uid2);
//        if (exists) {
//            System.out.println("黑名单存在");
//        } else {
//            System.out.print("黑名单不存在");
//        }
//        List<Blacklist> blacklist = blacklistService.getAllBlockedUsersByUserId(uid1);
//        return blacklist.stream().map(Blacklist::getBlockedUserId).collect(Collectors.toList());
//        System.out.println(blacklistByUser);
    }

    @GetMapping("/no-pipeline-test")
    public void noPipelineTest(@RequestParam("uid1") Integer uid1, @RequestParam("uid2") Integer uid2) {
        blacklistService.removeBlockedUser(uid1, uid2);
    }
    //新增数据
    @PostMapping("/hello")
    public HelloModel create(@RequestParam("title") String title,
                             @RequestParam("message") String message){
        HelloModel hellomodel = new HelloModel();
        hellomodel.setTitle(title);
        hellomodel.setMessage(message);
        return  helloRepository.save(hellomodel);
    }

    //获取数据列表
    @GetMapping("/hello")
    public List<HelloModel> list(){
        return helloRepository.findAll();
    }

    //通过ID查询item
    @GetMapping("/hello/{id}")
    public HelloModel findById(@PathVariable("id") Integer id){
        return helloRepository.findById(id).orElse(null);
    }

    //通过ID更新
    @PutMapping("/hello/{id}")
    public HelloModel update(@PathVariable("id") Integer id,
                             @RequestParam("title") String title, @RequestParam("message") String message){
        Optional<HelloModel> optional = helloRepository.findById(id);
        if(optional.isPresent()){
            HelloModel hellomodel = optional.get();
            hellomodel.setTitle(title);
            hellomodel.setMessage(message);
            return helloRepository.save(hellomodel);
        }
        return null;
    }

    @GetMapping("/friend-token-test")
    public String friendTokenTest(@RequestParam("uid1") Long uid1, @RequestParam("uid2") Long uid2) {
        System.out.println(GeneralUtils.getFriendshipToken(uid1, uid2));
        return GeneralUtils.getFriendshipToken(uid1, uid2);
    }

    //通过ID删除
    @DeleteMapping("/hello/{id}")
    public boolean delete(@PathVariable("id") Integer id) {
        Optional<HelloModel> optional = helloRepository.findById(id);
        if (optional.isPresent()) {
            helloRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @GetMapping("/enum-test")
    public GenderEnum getGenderEnum() {
        return GenderEnum.MALE;
    }

    @PostMapping("/enum-test")
    public void postGenderEnum(@RequestParam GenderEnum gender) {
        System.out.println(gender);
        System.out.println("调用成功");
    }

}
