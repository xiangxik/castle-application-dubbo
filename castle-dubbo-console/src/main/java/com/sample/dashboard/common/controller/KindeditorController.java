package com.sample.dashboard.common.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.castle.plugin.storage.support.FileInfo;
import com.castle.plugin.storage.support.FileService;

@Controller
@RequestMapping("/kindeditor")
public class KindeditorController {

	private final static String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };

	@Autowired
	private FileService fileService;

	@Value("${file.url?:}")
	private String fileUrl;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadImage(@RequestPart("imgFile") MultipartFile part,
			@RequestParam(value = "dir", required = false) String dir) {
		if (part == null) {
			return error("请选择文件");
		}
		String url = fileService.upload(part);
		return success(url);
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public Map<String, Object> listFile(@RequestParam("dir") String dir, @RequestParam("path") String path,
			@RequestParam("order") String order) {

		List<FileInfo> fileInfos = fileService.browser(path, order);

		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		Map<String, Object> result = new HashMap<>();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", fixUrl(fileUrl, path));
		result.put("total_count", fileInfos.size());

		List<Map<String, Object>> fileList = new ArrayList<>();
		if (fileInfos != null) {
			for (FileInfo fileInfo : fileInfos) {
				Map<String, Object> hash = new HashMap<>();
				if (fileInfo.getIsDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", true);
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else {
					String fileExt = FilenameUtils.getExtension(fileInfo.getName()).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", fileInfo.getSize());
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileInfo.getName());
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fileInfo.getLastModified()));
				fileList.add(hash);
			}
		}
		result.put("file_list", fileList);

		return result;
	}

	protected String fixUrl(String url1, String url2) {
		if (StringUtils.endsWith(url1, "/")) {
			if (StringUtils.startsWith(url2, "/")) {
				url2 = StringUtils.removeStart(url2, "/");
			}
		} else {
			if (!StringUtils.startsWith(url1, "/")) {
				url2 = "/" + url2;
			}
		}
		return url1 + url2;
	}

	private Map<String, Object> error(String message) {
		Map<String, Object> result = new HashMap<>();
		result.put("error", 1);
		result.put("message", message);
		return result;
	}

	private Map<String, Object> success(String url) {
		Map<String, Object> result = new HashMap<>();
		result.put("error", 0);
		result.put("url", url);
		return result;
	}

	public class NameComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
			}
		}
	}

	public class SizeComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	public class TypeComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
			if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
			}
		}
	}
}
