package com.bug.controller;

import com.bug.exeption.ProjectException;
import com.bug.model.*;
import com.bug.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@SessionAttributes(types = Employee.class)
public class IssueController {

    @Autowired
    ProjectService projectService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    IssueService issueService;
    @Autowired
    CommentService commentService;
    @Autowired
    AttachmentService attachmentService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");

    @RequestMapping(value = {"/saveInSession"})
    public void saveInSession(HttpServletResponse response, @AuthenticationPrincipal User user, Model model) throws IOException {
        Employee employee = employeeService.findByMail(user.getUsername());
        model.addAttribute(employee);
        response.sendRedirect("/");
    }

    @RequestMapping(value = {"/comments"})
    public String getComments(@RequestParam String issueId, Model model) throws IOException {

        model.addAttribute("comments", commentService.findCommentsByIssueId(Integer.parseInt(issueId)));
        return "comments";
    }

    @RequestMapping(value = {"/saveComment"})
    public void saveComment(@RequestParam String comment, @RequestParam Integer issueId, Employee employee) throws IOException {
        String createdBy = String.format("%s %s", employee.getName(), employee.getSurname());
        String createDate = String.valueOf(dateFormat.format(new Date()));
        commentService.insert(new Comment(comment, createdBy, createDate, issueId));
    }

    @RequestMapping(value = {"/issueSingle"})
    public String getIssue(@RequestParam String issueId, Model model) {
        boolean isAdmin = false;
        boolean isAuthenticate;
        boolean isUser = false;
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isAdmin = user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMINISTRATOR"));
            isUser = user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"));
        } catch (Exception ignored) {
        }
        isAuthenticate = isAdmin || isUser;
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthenticate", isAuthenticate);
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("issue", issueService.findById(Integer.parseInt(issueId)));
        return "issueSingle";
    }

    @RequestMapping(value = "/issues/issue")
    public String editIssue(@RequestParam String issueId, Model model) {
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("issue", issueService.findById(Integer.parseInt(issueId)));
        return "editIssue";
    }

    @RequestMapping(value = "/getBuildVersion")
    public String getBuildVersion(@RequestParam("projectName") String projectName, Model model) {
        Project project = projectService.findByName(projectName);
        model.addAttribute("builds", project.getBuildVersions());
        return "buildVersionlist";
    }

    @RequestMapping(value = {"/"})
    public String getIssuesList(Model model, Employee employee) {
        boolean isAdmin = false;
        boolean isAuthenticate;
        boolean isUser = false;
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isAdmin = user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMINISTRATOR"));
            isUser = user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"));
        } catch (Exception ignored) {
        }
        isAuthenticate = isAdmin || isUser;
        model.addAttribute("employee", employee);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isUser", isUser);
        model.addAttribute("isAuthenticate", isAuthenticate);
        model.addAttribute("issues", issueService.findAll());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        return "issues";
    }

    @RequestMapping(value = "issue/add/new")
    @ResponseBody
    public String addNewIssue(@RequestParam("summary") String summary, @RequestParam("description") String description,
                              @RequestParam("status") String status, @RequestParam("type") String type,
                              @RequestParam("priority") String priority, @RequestParam("projectName") String projectName,
                              @RequestParam("buildFound") String buildFound, @RequestParam("assignee") String assignee,
                              Employee employee) throws ProjectException {

        if (!summary.isEmpty() && !description.isEmpty()) {
            if (!status.isEmpty() && !type.isEmpty()) {
                String createdBy = String.format("%s %s", employee.getName(), employee.getSurname());
                String createDate = String.valueOf(dateFormat.format(new Date()));
                Issue issue = new Issue(createDate, createdBy, summary, description, status, type, priority, projectName, buildFound);
                if (!assignee.isEmpty()) issue.setAssignee(assignee);
                issueService.save(issue);
            } else {
                throw new ProjectException("status or type is empty");
            }
        } else {
            throw new ProjectException("summary or description is empty");
        }
        return "success";
    }

    @RequestMapping(value = "/issue/update")
    @ResponseBody
    public String addEditIssue(@RequestParam("summary") String summary, @RequestParam("description") String description,
                               @RequestParam("status") String status, @RequestParam("type") String type,
                               @RequestParam("priority") String priority, @RequestParam("projectName") String projectName,
                               @RequestParam("buildFound") String buildFound, @RequestParam("assignee") String assignee,
                               @RequestParam("id") Integer id, @RequestParam("resolution") String resolution, Employee employee) throws ProjectException {

        if (!summary.isEmpty() && !description.isEmpty()) {
            if (!status.isEmpty() && !type.isEmpty()) {
                Issue issue = new Issue(summary, description, status, type, priority, projectName);
                String modifiedBy = String.format("%s %s", employee.getName(), employee.getSurname());
                String modifyDate = String.valueOf(dateFormat.format(new Date()));
                issue.setModifiedBy(modifiedBy);
                issue.setModifyDate(modifyDate);
                if (!assignee.isEmpty()) issue.setAssignee(assignee);
                if (!buildFound.isEmpty()) issue.setBuildFound(buildFound);
                issue.setResolution(resolution);
                issueService.update(issue, id);
            } else {
                throw new ProjectException("status or type is empty");
            }
        } else {
            throw new ProjectException("summary or description is empty");
        }
        return "success";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(MultipartHttpServletRequest request, @RequestParam Integer issueId, Employee employee) throws IOException {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        for (MultipartFile multipartFile : fileMap.values()) {
            byte[] bytes = multipartFile.getBytes();
            String addedBy = String.format("%s %s", employee.getName(), employee.getSurname());
            String addedDate = String.valueOf(dateFormat.format(new Date()));
            try {
                attachmentService.save(new Attachment(bytes, multipartFile.getOriginalFilename(), addedBy, addedDate, issueId, multipartFile.getContentType()));
            } catch (Exception e) {
                return "file already exist";
            }
        }
        return "true";
    }

    @RequestMapping(value = "/get/file", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response, @RequestParam("fileId") Integer fileId) {

        Attachment attachment = attachmentService.findOne(fileId);

        try {
            response.setContentType(attachment.getType());
            response.setHeader("Content-disposition", "attachment; filename=\"" + attachment.getName()
                    + "\"");
            FileCopyUtils.copy(attachment.getMultipartFile(), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = {"/uploadFileList"})
    public String listFiles(@RequestParam("issueId") Integer issueId, Model model) {
        model.addAttribute("files", attachmentService.findAttachmentByIssueId(issueId));
        return "uploadFileList";
    }

    @RequestMapping(value = {"/deleteFile"})
    @ResponseBody
    public void deleteFile(@RequestParam("fileName") String fileName) {
        attachmentService.delete(fileName);
    }
}
