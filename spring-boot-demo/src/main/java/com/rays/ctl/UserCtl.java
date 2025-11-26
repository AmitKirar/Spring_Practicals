package com.rays.ctl;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.AttachmentService;
import com.rays.service.RoleService;
import com.rays.service.UserService;

@RestController
@RequestMapping(value = "user")
public class UserCtl extends BaseCtl {

	@Autowired
	public UserService userService;

	@Autowired
	public RoleService roleService;

	@Autowired
	public AttachmentService attachmentService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid UserForm form, BindingResult bindingResult) {
		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = (UserDTO) form.getDto();

		if (dto.getId() != null && dto.getId() > 0) {
			userService.update(dto);
			res.addData(dto.getId());
			res.addMessage("data updated successfully");
			res.setSuccess(true);

		} else {
			long pk = userService.add(dto);
			res.addData(pk);
			res.addMessage("data added successfully");
			res.setSuccess(true);
		}
		return res;

	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {
		ORSResponse res = new ORSResponse();

		for (long id : ids) {
			userService.delete(id);
		}

		res.addMessage("data deleted successfully");
		res.setSuccess(true);
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {
		ORSResponse res = new ORSResponse();
		UserDTO dto = userService.findById(id);

		if (dto != null) {
			res.setSuccess(true);
		}

		res.addData(dto);
		return res;
	}

	@PostMapping("/profile/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) {

		AttachmentDTO attachmentDTO = new AttachmentDTO(file);

		attachmentDTO.setDescription("profile pic");

		attachmentDTO.setUserId(userId);

		UserDTO userDto = userService.findById(userId);

		if (userDto.getImageId() != null && userDto.getImageId() > 0) {

			attachmentDTO.setId(userDto.getImageId());
		}

		Long imageId = attachmentService.save(attachmentDTO);

		if (userDto.getImageId() == null) {
			userDto.setImageId(imageId);
			userService.update(userDto);
		}

		ORSResponse res = new ORSResponse();

		res.addResult("imageId", imageId);
		res.setSuccess(true);
		return res;
	}
	
	
	@GetMapping("/profilePic/{userId}")
	public void downloadPic(@PathVariable Long userId, HttpServletResponse response) {

		try {

			UserDTO userDto = userService.findById(userId);

			AttachmentDTO attachmentDTO = null;

			if (userDto != null) {
				attachmentDTO = attachmentService.findById(userDto.getImageId());
			}

			if (attachmentDTO != null) {
				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();
			} else {
				response.getWriter().write("ERROR: File not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
