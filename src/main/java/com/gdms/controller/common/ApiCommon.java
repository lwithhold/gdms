package com.gdms.controller.common;

import cc.openkit.common.KitUtil;
import com.gdms.service.file.FileService;
import com.gdms.service.web.WebSettingService;
import com.gdms.util.StaticFinalVar;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
        * 这是上传的公共接口
        *
        * 1. 图片上传接口
        *
        *
        */

@Controller
@Scope("prototype")
@RequestMapping("/apiCommon")
public class ApiCommon {

    private Logger log = Logger.getLogger(ApiCommon.class);

    @Autowired
    private ServletContext servletContext;
    @Autowired
    private FileService fileService;
    @Autowired
    private WebSettingService webSettingService;

    /**
     * 图片上传接口 -- 用于layui
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setImage", method = RequestMethod.POST)
    @ResponseBody
    public Object getAbouts(@RequestParam("layuiFile") MultipartFile layuiFile) throws Exception {
        log.info("这里是保存图片的公共接口，我准备保存图片啦");
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        // 获取内容
        // 保存前台传来的缩略图
        String img = saveFile(layuiFile); // 获取图片地址
        map1.put("src", img);
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", map1);
        return map;
    }


    /**
     * 文件上传接口 -- 用于layui
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setFile", method = RequestMethod.POST)
    @ResponseBody
    public Object setFile(@RequestParam("layuiFile") MultipartFile layuiFile) throws Exception {
        log.info("这里是保存文件的公共接口，我准备保存文件啦");
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        // 获取内容
        String fileUrl = saveTFile(layuiFile); // 获取文件地址
        String fileName = layuiFile.getOriginalFilename();
        map1.put("fileUrl",fileUrl);
        map.put("code", 200);
        map.put("msg", "");
        map1.put("fileName",fileName);
        map.put("data", map1);
        return map;
    }

    /**
     * 把 HTTP 请求中的文件流保存到本地
     *
     * @param file MultipartFile 的对象
     */
    private String saveTFile(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // getRealPath() 取得 WEB-INF 所在文件夹路径
                // 如果参数是 "/temp", 当 temp 存在时返回 temp 的本地路径, 不存在时返回 null/temp (无效路径)
                String oldname=file.getOriginalFilename();
                String suffixName = oldname.substring(oldname.lastIndexOf("."));
                System.out.println("suffixName:"+suffixName);
                String name = KitUtil.uuid();
                // 截取文件格式
//                String type1 = file.getContentType();
//                String type = type1.substring(6);
                System.out.println("oldname:"+oldname);
                String gdmspath="E:/IDEA/WorkSpace/gdms/src/main/webapp/";
                String path =  gdmspath+ StaticFinalVar.FILE + name + suffixName;
                System.out.println("path:"+path);
//                File localFile = new File(path);
//                file.transferTo(localFile);
                FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(path));
                String fileurl =name + suffixName;
                System.out.println("file>>>>>>>>>>>>>" + name + suffixName);
                System.out.println(fileurl);
                return fileurl;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 把 HTTP 请求中的文件流保存到本地
     *
     * @param file MultipartFile 的对象
     */
    private String saveFile(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // getRealPath() 取得 WEB-INF 所在文件夹路径
                // 如果参数是 "/temp", 当 temp 存在时返回 temp 的本地路径, 不存在时返回 null/temp (无效路径)
                String name = KitUtil.uuid();
                // 截取文件格式
                String type1 = file.getContentType();
                String type = type1.substring(6);
//                String path2=getClass().getResource(".").getFile().toString();
                String gdmspath="E:/IDEA/WorkSpace/gdms/src/main/webapp/";
//                String tomcatpath="E:/工具软件/apache-tomcat-8.0.41/webapps/gdms/";
                String path =  gdmspath+ StaticFinalVar.IMG_FILES + name + "." + type;
//                String tpath=  tomcatpath+ StaticFinalVar.IMG_FILES + name + "." + type;
                System.out.println("path:"+path);
//                System.out.println("path2"+path2);
//                System.getProperty("user.dir");
//                System.out.println("file:"+file);
                FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(path));
//                FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(tpath));
                String imgurl = StaticFinalVar.IMG_FILES + name + "." + type;
//                System.out.println("file>>>>>>>>>>>>>" + name + "." + type);
//                System.out.println(imgurl);
                return imgurl;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 文件下载接口
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> download(@RequestParam(value = "fileName",required = false) String fileName,@RequestParam(value = "fileUrl",required = false) String fileUrl)throws Exception {
        //下载文件路径
//        String path = request.getServletContext().getRealPath("");
        String path="E:/IDEA/WorkSpace/gdms/src/main/webapp/uplodefiles/file";
        File file = new File(path+File.separator+fileUrl);
//        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        HttpHeaders headers = new HttpHeaders();
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //通知浏览器以attachment（下载方式）打开文件
        headers.setContentDispositionFormData("attachment", downloadFielName);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

//    @RequestMapping("/download")
//    public String download(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setCharacterEncoding("utf-8");
//        //返回的数据类型
//        response.setContentType("multipart/form-data");
//        //响应头
//        response.setHeader("Content-Disposition", "attachment;fileName="
//                + fileName);
//        InputStream inputStream=null;
//        OutputStream outputStream=null;
//        //路径
////        String path ="D:"+ File.separator+"imgs"+File.separator;
//        String path= request.getServletContext().getRealPath("/uplodefiles/file");
//        System.out.println("path:"+path);
//        byte[] bytes = new byte[2048];
//        try {
//            File file=new File(path,fileName);
//            inputStream = new FileInputStream(file);
//            outputStream = response.getOutputStream();
//            int length;
//            //inputStream.read(bytes)从file中读取数据,-1是读取完的标志
//            while ((length = inputStream.read(bytes)) > 0) {
//                //写数据
//                outputStream.write(bytes, 0, length);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            //关闭输入输出流
//            if(outputStream!=null) {
//                outputStream.close();
//            }
//            if(inputStream!=null) {
//                inputStream.close();
//            }
//        }
//        return null;
//    }

//    /**
//     * 图片上传 -- 用于我们的wangEditor
//     * 多张图片上传
//     *
//     * @param file
//     * @param request
//     * @return
//     */
//    @RequestMapping("/wUeSetImg")
//    @ResponseBody
//    public Object uploadFile(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) {
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        String[] strings = new String[file.length];
//
//        // 获取文件上传配置
//        WebSetting webSetting = webSettingService.queryById(1);
//
//        if (webSetting.getKitWebFile() == 1) {
//            // 保存到本地
//            for (int i = 0; i < file.length; i++) {
//                String url = saveFile(file[i]);
//                if (url == null) {
//                    map.put("errno", 0);
//                    return JSONObject.toJSON(map);
//                }
//                strings[i] = url;
//            }
//        } else if (webSetting.getKitWebFile() == 2) {
//            // 保存到七牛云
//            FileQiniu fileQiniu = fileQiniuService.queryById(1);
//            QiniuConfig config = new QiniuConfig(fileQiniu.getFqAccesskey(), fileQiniu.getFqSecretkey(), fileQiniu.getFqBucket());
//            for (int i = 0; i < file.length; i++) {
//
//                Map<String, Object> qiniumap = QIniuService.uploadByFileRecorder(config, cc.openkit.common.KitFileUtil.getMultipartFileToBytes(file[i]), fileQiniu.getFqZone());
//                DefaultPutRet putRet = (DefaultPutRet) qiniumap.get("putRet");
//                if (!"200".equals(qiniumap.get("code"))) {
//                    map.put("errno", 0);
//                    return JSONObject.toJSON(map);
//                }
//                strings[i] = fileQiniu.getFqUrl() + putRet.hash;
//            }
//        }
//        // 全部上传成功保存
//        map.put("errno", 0);
//        map.put("data", strings);
//
//        System.out.println(JSONObject.toJSON(map));
//
//        return JSONObject.toJSON(map);
//    }
//
//    /**
//     * app端图片上传一张或者多张都可以
//     *
//     * @param request
//     * @param file
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "/appSetImg", method = RequestMethod.POST)
//    @ResponseBody
//    public Object appSetImg(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) {
//
//        if (!AppUtil.isApp(request)) {
//            return JSONObject.toJSON(KitUtil.returnMap("4001", StaticFinalVar.APP_ERR));
//        }
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        String[] strings = new String[file.length];
//
//        for (int i = 0; i < file.length; i++) {
//            // 获取参
//            String url = saveFile(file[i]);
//            if (url == null) {
//                map.put("code", 101);
//                map.put("msg", StaticFinalVar.SERVICE_ERR);
//                return JSONObject.toJSON(map);
//            }
//            strings[i] = url;
//        }
//
//        map.put("code", 200);
//        map.put("msg", "");
//        map.put("imgUrl", strings);
//
//        return JSONObject.toJSON(map);
//    }

//
//    /**
//     * 新的文本编辑器 上传文件
//     */
//    @RequestMapping("/upload_json")
//    @ResponseBody
//    public Object upload_json(@RequestParam("imgFile") MultipartFile[] imgFile, HttpServletRequest request, HttpServletResponse response) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        Map<String, Object> remap = new HashMap<String, Object>();
//
//        // 获取文件上传配置
//        WebSetting webSetting = webSettingService.queryById(1);
//        if (webSetting.getKitWebFile() == 1) {
//            // 保存到本地需要吊桶的方法
//            KitFileUtil kfu = new KitFileUtil();
//            map = kfu.kitFileUtil(imgFile, request, response);
//
//            if ("101".equals(map.get("code"))) {
//                remap.put("error", 1);
//                remap.put("message", map.get("msg"));
//                return remap;
//            }
//            remap.put("error", 0);
//            String url[] = (String[]) map.get("url");
//            remap.put("url", url[0]);
//            return JSONObject.toJSON(remap);
//        } else if ((webSetting.getKitWebFile() == 2)) {
//            // 保存到七牛云
//            FileQiniu fileQiniu = fileQiniuService.queryById(1);
//            QiniuConfig config = new QiniuConfig(fileQiniu.getFqAccesskey(), fileQiniu.getFqSecretkey(), fileQiniu.getFqBucket());
//
//            for (int i = 0; i < imgFile.length; i++) {
//                Map<String, Object> qiniumap = QIniuService.uploadByFileRecorder(config, cc.openkit.common.KitFileUtil.getMultipartFileToBytes(imgFile[i]), fileQiniu.getFqZone());
//                DefaultPutRet putRet = (DefaultPutRet) qiniumap.get("putRet");
//                if (!"200".equals(qiniumap.get("code"))) {
//                    remap.put("error", 1);
//                    remap.put("message", map.get("msg"));
//                    return remap;
//                }
//                String url = fileQiniu.getFqUrl() + putRet.hash;
//                remap.put("error", 0);
//                remap.put("url", KitUtil.remove(url, ' '));
//                System.out.println("返回的链接地址：" + remap.get("url"));
//                System.out.println("返回的链接地址：" + fileQiniu.getFqUrl());
//                return JSONObject.toJSON(remap);
//            }
//        }
//        remap.put("error", 1);
//        remap.put("message", map.get("msg"));
//        return remap;
//    }
//
//    /**
//     * 处理编辑器方法
//     */
//    @RequestMapping("/file_manager_json")
//    @ResponseBody
//    public Object file_manager_json(@RequestParam("imgFile") MultipartFile[] imgFile, HttpServletRequest request, HttpServletResponse response) {
//        Map<String, Object> map = new HashMap<String, Object>();
//
//
////根目录路径，可以指定绝对路径，比如 /var/www/attached/
//        String rootPath = servletContext.getRealPath("") + "uplodefiles/";
////根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
//        String rootUrl = request.getContextPath() + "/uplodefiles/";
////图片扩展名
//        String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};
//
//        String dirName = request.getParameter("dir");
//        if (dirName != null) {
//            if (!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)) {
//                System.out.println("Invalid Directory name.");
////                out.println("Invalid Directory name.");
//                return null;
//            }
//            rootPath += dirName + "/";
//            rootUrl += dirName + "/";
//            File saveDirFile = new File(rootPath);
//            if (!saveDirFile.exists()) {
//                saveDirFile.mkdirs();
//            }
//        }
////根据path参数，设置各路径和URL
//        String path = request.getParameter("path") != null ? request.getParameter("path") : "";
//        String currentPath = rootPath + path;
//        String currentUrl = rootUrl + path;
//        String currentDirPath = path;
//        String moveupDirPath = "";
//        if (!"".equals(path)) {
//            String str = currentDirPath.substring(0, currentDirPath.length() - 1);
//            moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
//        }
//
////排序形式，name or size or type
//        String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";
//
////不允许使用..移动到上一级目录
//        if (path.indexOf("..") >= 0) {
//            System.out.println("Access is not allowed.");
////            out.println("Access is not allowed.");
//            return null;
//        }
////最后一个字符不是/
//        if (!"".equals(path) && !path.endsWith("/")) {
//            System.out.println("Access is not allowed.");
////            out.println("Parameter is not valid.");
//            return null;
//        }
////目录不存在或不是目录
//        File currentPathFile = new File(currentPath);
//        if (!currentPathFile.isDirectory()) {
//            System.out.println("Access is not allowed.");
////            out.println("Directory does not exist.");
//            return null;
//        }
//
////遍历目录取的文件信息
//        List<Hashtable> fileList = new ArrayList<Hashtable>();
//        if (currentPathFile.listFiles() != null) {
//            for (File file : currentPathFile.listFiles()) {
//                Hashtable<String, Object> hash = new Hashtable<String, Object>();
//                String fileName = file.getName();
//                if (file.isDirectory()) {
//                    hash.put("is_dir", true);
//                    hash.put("has_file", (file.listFiles() != null));
//                    hash.put("filesize", 0L);
//                    hash.put("is_photo", false);
//                    hash.put("filetype", "");
//                } else if (file.isFile()) {
//                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//                    hash.put("is_dir", false);
//                    hash.put("has_file", false);
//                    hash.put("filesize", file.length());
//                    hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
//                    hash.put("filetype", fileExt);
//                }
//                hash.put("filename", fileName);
//                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
//                fileList.add(hash);
//            }
//        }
//
//        if ("size".equals(order)) {
//            Collections.sort(fileList, new SizeComparator());
//        } else if ("type".equals(order)) {
//            Collections.sort(fileList, new TypeComparator());
//        } else {
//            Collections.sort(fileList, new NameComparator());
//        }
//        JSONObject result = new JSONObject();
//        result.put("moveup_dir_path", moveupDirPath);
//        result.put("current_dir_path", currentDirPath);
//        result.put("current_url", currentUrl);
//        result.put("total_count", fileList.size());
//        result.put("file_list", fileList);
//
//        response.setContentType("application/json; charset=UTF-8");
//        System.out.println(result.toJSONString());
////        out.println(result.toJSONString());
//
//        return JSONObject.toJSON(map);
//
//    }
//
//
//    public class NameComparator implements Comparator {
//        public int compare(Object a, Object b) {
//            Hashtable hashA = (Hashtable) a;
//            Hashtable hashB = (Hashtable) b;
//            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
//                return -1;
//            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
//                return 1;
//            } else {
//                return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
//            }
//        }
//    }
//
//    public class SizeComparator implements Comparator {
//        public int compare(Object a, Object b) {
//            Hashtable hashA = (Hashtable) a;
//            Hashtable hashB = (Hashtable) b;
//            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
//                return -1;
//            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
//                return 1;
//            } else {
//                if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
//                    return 1;
//                } else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
//                    return -1;
//                } else {
//                    return 0;
//                }
//            }
//        }
//    }
//
//    public class TypeComparator implements Comparator {
//        public int compare(Object a, Object b) {
//            Hashtable hashA = (Hashtable) a;
//            Hashtable hashB = (Hashtable) b;
//            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
//                return -1;
//            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
//                return 1;
//            } else {
//                return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
//            }
//        }
//    }
}