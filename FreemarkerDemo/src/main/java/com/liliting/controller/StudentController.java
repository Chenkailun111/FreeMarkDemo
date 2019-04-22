package com.liliting.controller;

import com.liliting.dao.StudentDao;
import com.liliting.pojo.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖镇楼                  BUG辟易
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 *
 * @Description:
 * @Company: 千锋互联
 * @Author: 李丽婷
 * @Date: 2018/12/13
 * @Time: 下午2:15
 */
@Controller
public class StudentController {

    @Resource
    FreeMarkerConfigurer configurer;

    /**
     * 作为数据渲染  展示数据
     * @param model
     * @return
     */
    @RequestMapping("/findStu")
    public String find(Model model){
        model.addAttribute("name","admin");

        List<Student> list = StudentDao.getList();
        model.addAttribute("stus",list);

        return "stuList";
    }

    /**
     * 演示freemarker的第二个功能
     * 使用这个模板引擎实现页面的静态化
     * 模板+数据模型----》html文件
     * 作用范围，经常展示的同一个页面
     * @return
     */
    @RequestMapping("/getHtml")
    @ResponseBody
    public String getHtml(){
        try {
            //得到模板对象
            Configuration configuration =  configurer.getConfiguration();
            Template template= configuration.getTemplate("stuList.ftl");

            //得到数据模型
            List<Student> list = StudentDao.getList();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("stus",list);
            map.put("name","尼古拉斯.赵四");
            //生成html文件
            //  FileWriter fw = new FileWriter("E:\\stuList.html");
            //原来的有中文乱码(默认gbk，但展示是utf-8)，解决中文乱码
            File file1 = new File("E:\\stuList.html");
            Writer fw =
            new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1), "UTF-8"));

            template.process(map,fw);
            System.out.println("success");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
