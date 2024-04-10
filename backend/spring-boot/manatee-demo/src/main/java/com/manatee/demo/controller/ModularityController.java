package com.manatee.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.manatee.lowcode.domain.WebResult;
import com.manatee.lowcode.util.ManateeExportUtil;
import com.manatee.lowcode.util.WebUtil;
import com.manatee.lowcode.web.manager.CustomizeModuleManager;
import com.manatee.process.constant.ProcessConstant;
import com.manatee.process.domain.ProcessContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.manatee.process.constant.ProcessConstant.Key.FILE;

/**
 * Created by ziyue on 2018/6/2.
 */
@Controller
@RequestMapping("/biz")
public class ModularityController {

    @Autowired
    private CustomizeModuleManager customizeModuleManager;

    @Autowired
    private ManateeExportUtil manateeExportUtil;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/modularity/project{projectId}/{code}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject doModularityGet(HttpServletRequest request, HttpServletResponse response,
                                      @PathVariable Long projectId,
                                      @PathVariable String code) {
        ProcessContent content = new ProcessContent();
        initParam(content,request);
        content.putTempContent(ProcessConstant.Key.CODE, code);
        content.putTempContent(ProcessConstant.Key.PROJECT_ID, projectId);
        JSONObject result = customizeModuleManager.customize(request,content);
        boolean committed = response.isCommitted();
        return committed?null:result;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/modularity/project{projectId}/{code}", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject doModularityPost(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody(required = false) JSONObject data,
                                       @PathVariable Long projectId,
                                       @PathVariable String code) {
        ProcessContent content = new ProcessContent();
        initParam(content,request);
        content.putRequest(data);
        content.putTempContent(ProcessConstant.Key.CODE, code);
        content.putTempContent(ProcessConstant.Key.PROJECT_ID, projectId);
        JSONObject result = customizeModuleManager.customize(request,content);
        boolean committed = response.isCommitted();
        return committed?null:result;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/modularityUpload/project{projectId}/{code}", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject doModularityUpload(@RequestParam(FILE) MultipartFile file, HttpServletRequest request,
                                         @PathVariable Long projectId,
                                         @PathVariable String code) {
        ProcessContent content = new ProcessContent();
        try {
            initParam(content,request);
            content.putTempContent(ProcessConstant.Key.CODE, code);
            content.putTempContent(ProcessConstant.Key.PROJECT_ID, projectId);
            content.setFile(file.getInputStream());
            content.setFileName(file.getOriginalFilename());
            JSONObject result = customizeModuleManager.customize(request, content);
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
            content.addException(e);
        }
        WebResult result = WebUtil.wirteResult(content);
        return result.toJson();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/modularityExport/project{projectId}/{code}", method = RequestMethod.GET)
    @ResponseBody
    public void doModularityExport(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable Long projectId,
                                   @PathVariable String code) {
        ProcessContent content = new ProcessContent();
        try {
            initParam(content,request);
            content.putTempContent(ProcessConstant.Key.CODE, code);
            content.putTempContent(ProcessConstant.Key.PROJECT_ID, projectId);
            JSONObject customize = customizeModuleManager.customize(request, content);
            JSONObject result = getExportData(content,customize);
            manateeExportUtil.exprotToResponse(result,response);
        } catch (Throwable e) {
            e.printStackTrace();
            content.addException(e);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/modularityExport/project{projectId}/{code}", method = RequestMethod.POST)
    @ResponseBody
    public void doModularityExport(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody(required = false) JSONObject data,
                                   @PathVariable Long projectId,
                                   @PathVariable String code) {
        ProcessContent content = new ProcessContent();
        try {
            initParam(content,request);
            content.putRequest(data);
            content.putTempContent(ProcessConstant.Key.CODE, code);
            content.putTempContent(ProcessConstant.Key.PROJECT_ID, projectId);
            JSONObject customize = customizeModuleManager.customize(request, content);
            JSONObject result = getExportData(content,customize);
            manateeExportUtil.exprotToResponse(result,response);
        } catch (Throwable e) {
            e.printStackTrace();
            content.addException(e);
        }
    }

    protected void initParam(ProcessContent content, HttpServletRequest request){
        content.putTempContent("userId", 2);
    }

    protected JSONObject getExportData(ProcessContent content, JSONObject customize){
        String backStructure = content.getProjectConf().getString("backStructure");
        JSONObject backStructureJson = JSONObject.parseObject(backStructure);
        String dataKey = backStructureJson.getJSONObject("customKeys").getString("data");
        JSONObject result = customize.getJSONObject(dataKey);
        return result;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/customizeModule.do", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject doCustomizeModule(HttpServletRequest request, HttpServletResponse response) {
        ProcessContent content = new ProcessContent();
        return customizeModuleManager.customize(request, content);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/customizeModule.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject doCustomizeModulePost(HttpServletRequest request, HttpServletResponse response,
                                            @RequestBody(required = false) JSONObject data) {
        ProcessContent content = new ProcessContent();
        content.putRequest(data);
        return customizeModuleManager.customize(request, content);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/customizeModuleFile.do", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject doCustomizeModuleFile(@RequestParam(FILE) MultipartFile file,HttpServletRequest request) {
        ProcessContent content = new ProcessContent();
        try {
            content.setFile(file.getInputStream());
            content.setFileName(file.getOriginalFilename());
            return customizeModuleManager.customize(request, content);
        } catch (Throwable e) {
            e.printStackTrace();
            content.addException(e);
        }
        WebResult result = WebUtil.wirteResult(content);
        return result.toJson();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/customizeModuleExport.do", method = RequestMethod.GET)
    @ResponseBody
    public void customizeModuleExport(HttpServletRequest request,HttpServletResponse response) {
        ProcessContent content = new ProcessContent();
        try {
            JSONObject customize = customizeModuleManager.customize(request, content);
            JSONObject result = getExportData(content,customize);
            manateeExportUtil.exprotToResponse(result,response);
        } catch (Throwable e) {
            e.printStackTrace();
            content.addException(e);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/customizeModuleExport.do", method = RequestMethod.POST)
    @ResponseBody
    public void customizeModuleExportPost(HttpServletRequest request,HttpServletResponse response,
                                          @RequestBody(required = false) JSONObject data) {
        ProcessContent content = new ProcessContent();
        try {
            content.putRequest(data);
            JSONObject customize = customizeModuleManager.customize(request, content);
            JSONObject result = getExportData(content,customize);
            manateeExportUtil.exprotToResponse(result,response);
        } catch (Throwable e) {
            e.printStackTrace();
            content.addException(e);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/modularity/helloManatee", method = RequestMethod.GET)
    @ResponseBody
    public String helloManatee(){
        return "Hello Manatee!";
    }

}
