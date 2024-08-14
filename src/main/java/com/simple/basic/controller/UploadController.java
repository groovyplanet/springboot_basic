package com.simple.basic.controller;

import com.simple.basic.command.UploadVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/fileupload")
public class UploadController {

    @Value("${project.upload.path}") // application.properties에 있는 키값 받아옴
    private String uploadPath; //업로드 경로

    //폴더 생성 함수
    public String makeFolder(){
       String filepath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        File file = new File(uploadPath + "/" + filepath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return filepath;
    }



    @GetMapping("/upload")
    public String uploadView() {
        return "fileupload/upload";
    }

    //단일파일 업로드
    @PostMapping("/uploadOk")
    public String uploadOk(@RequestParam("file") MultipartFile file) {

        //1. 브라우저별로 , 사용자의 풀 경로가 제목에 포함되는 경우가 있습니다.(제외)
        //2. 동일한 파일에 대한 처리 , 동일한 이름이 올라오면 파일이 덮어씌워집니다. (처리)
        //3. 1개의 폴더에 저장 가능한 파일 수 는 6만개? (월별로 폴더를 생성해서 저장)

        String originName = file.getOriginalFilename(); // 파일 명
        String fileName = originName.substring(originName.lastIndexOf("\\")+1);
        String filePath = makeFolder(); //폴더명
        String uuid = UUID.randomUUID().toString(); // 랜덤 값

        String savePath = uploadPath + "/" + filePath +"/" +uuid +"_" + fileName; //업로드 경로



        // 파일명을 포함한 경로
        try {
            File path = new File(savePath);
            file.transferTo(path); // 파일 업로드
            //fileName , filePath , uuid 이 값은 db에 저장

        } catch (IOException e) {
           e.printStackTrace();
        }


        return "fileupload/upload_ok";
    }

    //다중파일 업로드
    @PostMapping("/uploadOk2")
    public String uploadOk2(MultipartHttpServletRequest files) {

        List<MultipartFile>list = files.getFiles("file"); // 폼 태그의 name 값
        for(MultipartFile file : list){
            //1. 브라우저별로 , 사용자의 풀 경로가 제목에 포함되는 경우가 있습니다.(제외)
            //2. 동일한 파일에 대한 처리 , 동일한 이름이 올라오면 파일이 덮어씌워집니다. (처리)
            //3. 1개의 폴더에 저장 가능한 파일 수 는 6만개? (월별로 폴더를 생성해서 저장)

            String originName = file.getOriginalFilename(); // 파일 명
            String fileName = originName.substring(originName.lastIndexOf("\\")+1);
            String filePath = makeFolder(); //폴더명
            String uuid = UUID.randomUUID().toString(); // 랜덤 값

            String savePath = uploadPath + "/" + filePath +"/" +uuid +"_" + fileName; //업로드 경로



            // 파일명을 포함한 경로
            try {
                File path = new File(savePath);
                file.transferTo(path); // 파일 업로드
                //fileName , filePath , uuid 이 값은 db에 저장

            } catch (IOException e) {
                e.printStackTrace();
            }

        }



        return "fileupload/upload_ok";
    }

    
    
    //복수태그로 여러파일 업로드
    @PostMapping("/uploadOk3")
    public String uploadOk3(@RequestParam("file")List<MultipartFile>list) {

        //업로드 전에 빈 태그 값은 지우고 다시 처리
      list =list.stream().filter(a->a.isEmpty()==false).collect(Collectors.toList());

        for(MultipartFile file : list){ ////////////

            String originName = file.getOriginalFilename(); // 파일 명
            String fileName = originName.substring(originName.lastIndexOf("\\")+1);
            String filePath = makeFolder(); //폴더명
            String uuid = UUID.randomUUID().toString(); // 랜덤 값

            String savePath = uploadPath + "/" + filePath +"/" +uuid +"_" + fileName; //업로드 경로



            // 파일명을 포함한 경로
            try {
                File path = new File(savePath);
                file.transferTo(path); // 파일 업로드
                //fileName , filePath , uuid 이 값은 db에 저장

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "fileupload/upload_ok";
    }

    //비동기 업로드
    //클라이언트에서는 form형식 multipart타입으로 보내고 , vo타입으로 받으면 됨
    @PostMapping("/uploadOk4")
    @ResponseBody // 일반 컨트롤러에서 Rest처럼 쓰고 싶다면
    public String uploadOk4(UploadVO vo)
            /*@RequestParam("file") MultipartFile file,
              @RequestParam("writer") String Writer*/
     {

         MultipartFile file = vo.getFile();
         String originName = file.getOriginalFilename(); // 파일 명
         String fileName = originName.substring(originName.lastIndexOf("\\")+1);
         String filePath = makeFolder(); //폴더명
         String uuid = UUID.randomUUID().toString(); // 랜덤 값

         String savePath = uploadPath + "/" + filePath +"/" +uuid +"_" + fileName; //업로드 경로



         // 파일명을 포함한 경로
         try {
             File path = new File(savePath);
             file.transferTo(path); // 파일 업로드
             //fileName , filePath , uuid 이 값은 db에 저장

         } catch (IOException e) {
             e.printStackTrace();
         }
        return "success";
    }

}
