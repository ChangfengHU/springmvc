package feng.springmvc.controllor;

import feng.springmvc.pojo.User;
import feng.springmvc.pojo.UserVO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("hello")
public class Hello2Controller {
	
	@RequestMapping(value="show1")
	public ModelAndView test1(){
		ModelAndView mv = new ModelAndView();
		System.out.println("Handler方法，正在执行");
		mv.setViewName("hello");
		mv.addObject("msg", "springmvc第一个注解程序");
		return mv;
	}
	
	@RequestMapping(value="s*/show2")
	public ModelAndView test2(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "springmvc第一个注解程序, ant风格：*");
		return mv;
	}
	
	@RequestMapping(value="**/show2")
	public ModelAndView test3(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "springmvc第一个注解程序, ant风格：**");
		return mv;
	}
	
	@RequestMapping(value="show4/{name}/{id}")
	public ModelAndView test4(@PathVariable("name") String name1,@PathVariable("id")Long id){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "springmvc第一个注解程序, 占位符的映射:name="+name1+",id="+id);
		return mv;
	}
	
	@RequestMapping(value="show5", method=RequestMethod.POST)
	public ModelAndView test5(){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "springmvc第一个注解程序, 限定请求方发：POST");
		return mv;
	}
	
	@RequestMapping(value="show6", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView test6(){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "springmvc第一个注解程序, 限定请求方发：POST/GET");
		return mv;
	}
	
	@RequestMapping(value="show8", params="id")
	public ModelAndView test8(){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "springmvc的映射之限定请求参数，id");
		return mv;
	}
	
	@RequestMapping(value="show9", params="!id")
	public ModelAndView test9(){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "springmvc的映射之限定请求参数，!id");
		return mv;
	}
	
	@RequestMapping(value="show10", params="id=1")
	public ModelAndView test10(){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "springmvc的映射之限定请求参数，id=1");
		return mv;
	}
	
	@RequestMapping(value="show11", params="id!=1")
	public ModelAndView test11(){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "springmvc的映射之限定请求参数，id!=1");
		return mv;
	}
	
	@RequestMapping(value="show12", params={"id","name"})
	public ModelAndView test12(){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "springmvc的映射之限定请求参数，id,name");
		return mv;
	}
	
	@RequestMapping(value="show13")
	public ModelAndView test13(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		ModelAndView mv = new ModelAndView("hello");
		StringBuffer sb = new StringBuffer();
		sb.append(request.toString()+"<br />");
		sb.append(response.toString()+"<br />");
		sb.append(session.toString());
		mv.addObject("msg", "springmvc的映射之限定请求参数，内置对象："+sb.toString());
		return mv;
	}
	
	@RequestMapping(value="show14")
	public String test14(Model model, ModelMap map){
		model.addAttribute("msg1", "springmvc的映射之限定请求参数，内置对象：model");
		map.addAttribute("msg", "springmvc的映射之限定请求参数，内置对象：map");
		return "hello";
	}
	
	@RequestMapping(value="show15/{name}/{id}")
	public ModelAndView test15(@PathVariable("name") String name1,@PathVariable("id")Long id){
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("msg", "springmvc第一个注解程序, 占位符的映射:name="+name1+",id="+id);
		return mv;
	}
	
	@RequestMapping(value="show16")
	public String test16(@RequestParam(value="name", required=false) String name, @RequestParam(value="id", defaultValue="22")Long id, Model model){
		model.addAttribute("msg", "springmvc第一个注解程序,接收普通参数，Id="+id+",name="+name);
		return "hello";
	}
	
	@RequestMapping(value="show17")
	public String test17(Model model, HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length!=0){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("JSESSIONID")){
					model.addAttribute("msg", "springmvc第一个注解程序,接收cookie参数JSESSONID="+cookie.getValue());
				}
			}
		}
//		model.addAttribute("msg", "springmvc第一个注解程序,接收普通参数，Id="+id+",name="+name);
		return "hello";
	}
	
	@RequestMapping(value="show18")
	public String test18(Model model, @CookieValue("JSESSIONID") String sessionId){
		model.addAttribute("msg", "springmvc第一个注解程序,接收cookie参数JSESSONID="+sessionId);
//		model.addAttribute("msg", "springmvc第一个注解程序,接收普通参数，Id="+id+",name="+name);
		return "hello";
	}
	
	@RequestMapping(value="show19")
	@ResponseStatus(value=HttpStatus.OK)
	public void test19(@RequestParam("name") String name,
			@RequestParam("age") Integer age,
			@RequestParam("isMarry") Boolean isMarry,
			@RequestParam("income") Double income,
			@RequestParam("interests") String[] interests
			){
		StringBuilder sb = new StringBuilder();
		sb.append("name:"+name+"\n");
		sb.append("age:"+age+"\n");
		sb.append("isMarry:"+isMarry+"\n");
		sb.append("income:"+income+"\n");
		sb.append("interests:");
		for (String interest : interests) {
			sb.append(interest+"  ");
		}
		System.out.println(sb.toString());
	}
	
	@RequestMapping(value="show20")
	@ResponseStatus(value=HttpStatus.OK)
	public void test20(User user){
		System.out.println(user.toString());
	}
	
	@RequestMapping(value="show21")
	public String test21(Model model, UserVO userVO){
		model.addAttribute("msg", "接收集合："+userVO.toString());
		return "hello";
	}
	
	@RequestMapping(value="show22")
	public String test22(Model model){
		List<User> userList = new ArrayList<>();
		for(int i=0;i<10;i++){
			User user = new User();
			user.setId(1+i);
			user.setUserName("zhangsan"+i);
			user.setName("张三"+i);
			user.setAge(10+i);
			userList.add(user);
		}
		model.addAttribute("userList", userList);
		return "user";
	}
	
	@RequestMapping(value="show23")
	@ResponseBody
	public List<User> test23(Model model){
		List<User> userList = new ArrayList<>();
		for(int i=0;i<10;i++){
			User user = new User();
			user.setId(1+i);
			user.setUserName("zhangsan"+i);
			user.setName("张三"+i);
			user.setAge(10+i);
			userList.add(user);
		}
		return userList;
	}
	
	@RequestMapping(value="show24")
	public String test24(Model model, @RequestBody User user){
		model.addAttribute("msg", user.toString());
		return "hello";
	}
	
	@RequestMapping(value="show25")
	public String test25(Model model, @RequestBody String user){
		model.addAttribute("msg", user);
		return "hello";
	}
	
	@RequestMapping(value="upload")
	public String upload(@RequestParam("file")MultipartFile file) throws IllegalStateException, IOException{
		if(file!=null){
			file.transferTo(new File("E:\\tmp\\"+file.getOriginalFilename()));
		}
		return "redirect:/success.html";
	}
	
	@RequestMapping(value="show26")
	public String test26(Model model){
		return "forward:show28.do?id=11&type=forward";
	}
	
	@RequestMapping(value="show27")
	public String test27(Model model){
		return "redirect:show28.do?id=22&type=redirect";
	}
	
	@RequestMapping(value="show28")
	public String test28(Model model, @RequestParam("id") Long id, @RequestParam("type")String type){
		model.addAttribute("msg", "id="+id+",type="+type);
		return "hello";
	}

}
