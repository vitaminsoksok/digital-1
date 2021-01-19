package kr.green.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.green.spring.service.UserService;
import kr.green.spring.vo.TestVo;
import kr.green.spring.vo.UserVo;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homeGet(ModelAndView mv, String name) {
		mv.setViewName("/main/login");
		return mv;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signupGet(ModelAndView mv) {
		mv.setViewName("/main/signup");
		return mv;
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signupPost(ModelAndView mv,UserVo user) {
		System.out.println(user);
		//회원가입을 진행시키기 위해 userService에게 일을 시키기위한 메소드를 생성해
		//보세요.
		boolean isSignup = userService.signup(user);
		if(isSignup) {
			mv.setViewName("redirect:/login");	
		}else {
			mv.setViewName("redirect:/signup");
		}
		return mv;
	}
	
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public ModelAndView userListGet(ModelAndView mv) {
		ArrayList<UserVo> list = new ArrayList<UserVo>();
		//모든 회원 정보를 가져오는 코드
		list = userService.getAllUser();
		mv.addObject("list",list);
		mv.setViewName("/main/list2");
		return mv;
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGet(ModelAndView mv) {
		mv.setViewName("/main/login");
		return mv;
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost(ModelAndView mv, String id, String pw) {
		//수정전 : id와 pw가 일치하는 회원이 있으면 true,
		//       없으면 false를 가져옴
		//수정후 : id와 pw가 일치하는 회원이 있으면 회원 정보를 가져오고,
		//       없으면 null을 가져옴
		//boolean isUser = userService.isUser(id, pw);
		UserVo isUser = userService.isUser(id, pw);
		
		mv.addObject("user", isUser);
		if(isUser != null) {
			mv.setViewName("redirect:/");//로그인 성공시
			//localhost:8080/spring/
		}else {
			mv.setViewName("redirect:/login");//로그인 실패시
			//localhost:8080/spring/login
		}
		return mv;
	}
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public ModelAndView signoutGet(ModelAndView mv,HttpServletRequest r) {
		//세션에 저장된 user 정보를 삭제
		r.getSession().removeAttribute("user");
		mv.setViewName("redirect:/");
		return mv;
	}
	@RequestMapping(value = "/ajax1", method = RequestMethod.POST)
	@ResponseBody
	public String ajax1Post(TestVo testVo) {
		System.out.println("ajax 테스트 성공!");
		System.out.println(testVo);
		return testVo.toString();
	}
	
	@RequestMapping(value = "/dup", method = RequestMethod.POST)
	@ResponseBody
	public String dupPost(String id) {
		UserVo user = userService.getUser(id);
		if(user == null)
			return "not user";
		return "user";
	}
	
	@RequestMapping(value = "/ajax2", method = RequestMethod.POST)
	@ResponseBody
	public Object ajax2Post(@RequestBody TestVo testVo) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		ArrayList<UserVo> list = userService.getAllUser();
		map.put("list", list);
		map.put("testVo", testVo);
		return map;
	}
	@RequestMapping(value = "/author/modify", method = RequestMethod.POST)
	@ResponseBody
	public Object authorModifyPost(@RequestBody UserVo userVo) {
		userService.updateAuthor(userVo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		return map;
	}
}





