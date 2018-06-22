package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.util.UUIDUtils;
import cn.tpson.kulu.gas.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Zhangka in 2018/06/08
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    private static final int MAX_PATH = 65536;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    /**
     * @param f
     * @return
     */
    @PostMapping("/upload")
    public ResultVO upload(@RequestParam("uploaded_file") MultipartFile f) {
        String filename = filename(f.getOriginalFilename());
        String userDir = System.getProperty("user.dir");
        int region = ThreadLocalRandom.current().nextInt(MAX_PATH) % MAX_PATH;
        String filepath = userDir + "/images/" + region + "/";
        Path path = Paths.get(filepath + filename);
        File file = new File(filepath);
        if (!file.exists()) {
            file.mkdir();
        }

        try (ReadableByteChannel in = Channels.newChannel(f.getInputStream());
             FileChannel out = FileChannel.open(path, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE))) {

            out.transferFrom(in, 0, f.getSize());
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("拷贝文件时出错", e);
            }
            return ResultVO.failResult("上传失败.");
        }

        return ResultVO.successResult("/gas/image/" + region + "/" + filename);
    }

    /**
     *
     * @param region
     * @param filename
     * @param resp
     * @return
     */
    @GetMapping("/{region}/{filename}")
    public ResultVO download(@PathVariable String region, @PathVariable String filename, HttpServletResponse resp) {
        String userDir = System.getProperty("user.dir");
        Path path = Paths.get(userDir + "/images/" + region + "/" + filename);

        try (WritableByteChannel out = Channels.newChannel(resp.getOutputStream());
             FileChannel in = FileChannel.open(path, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.READ))) {

            in.transferTo(0, in.size(), out);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("下载文件时出错", e);
            }
            return ResultVO.failResult("下载失败.");
        }

        return ResultVO.successResult();
    }

    //////////////////////////////////////////////////////////////////////////////////////
    private String filename(String fname) {
        /*int index = fname.lastIndexOf(".");
        String imageType = index == -1 ? "" : fname.substring(index, fname.length());*/
        return UUIDUtils.uuid() + "-" + fname;
    }
}
