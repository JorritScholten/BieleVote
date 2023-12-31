import { useEffect, useState } from "react";
import { Form, Button, Message } from "semantic-ui-react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";
import { HttpStatusCode } from "axios";
import DOMPurify from "dompurify";

import { emptyForms } from "../../../misc/ApiForms";
import { useAuth } from "../../../misc/AuthContext";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";
import { accountType } from "../../../misc/NavMappings";
import "./form_editor_styling.css";

function ProjectForm() {
  const [newProject, setNewProject] = useState(emptyForms.newProject);
  const [allowedToPost, setAllowedToPost] = useState(false);
  const [reasonsPostingDenied, setReasonsPostingDenied] = useState([]);
  const { getUser, getAccountType, userIsAuthenticated } = useAuth();

  async function onSubmit(e) {
    e.preventDefault();
    try {
      const sanitizedNewProject = {
        ...newProject,
        content: DOMPurify.sanitize(newProject.content),
      };
      const response = await backendApi.postProject(
        getUser(),
        sanitizedNewProject
      );
      if (response.status === HttpStatusCode.Created) {
        setNewProject(emptyForms.newProject);
        getAllowedToPost();
      }
    } catch (error) {
      handleLogError(error);
    }
  }

  async function getAllowedToPost() {
    try {
      const response = await backendApi.allowedToPostProject(getUser());
      if (response.status === HttpStatusCode.Ok) {
        setAllowedToPost(response.data);
        if (!response.data) getReasonsPostingDenied();
      }
    } catch (error) {
      handleLogError(error);
    }
  }

  async function getReasonsPostingDenied() {
    try {
      const response = await backendApi.deniedToPostProjectReasons(getUser());
      if (response.status === HttpStatusCode.Ok) {
        setReasonsPostingDenied(response.data);
      }
    } catch (error) {
      handleLogError(error);
    }
  }

  useEffect(() => {
    if (userIsAuthenticated()) getAllowedToPost();
  }, [userIsAuthenticated]);

  const modules = {
    toolbar: [
      [{ font: [] }],
      [{ header: ["1", "2", "3", false] }],
      ["bold", "italic", "underline"],
      [{ list: "ordered" }, { list: "bullet" }],
    ],
  };

  return (
    <Form onSubmit={onSubmit}>
      <Message negative hidden={allowedToPost}>
        <Message.Header content="Currently not allowed to propose a project due to:" />
        <Message.List>
          {reasonsPostingDenied.map((reason) => (
            <Message.Item key={reason} content={reason} />
          ))}
        </Message.List>
      </Message>
      <Form.Input
        disabled={!allowedToPost}
        label="Title:"
        name="title"
        placeholder="Title"
        value={newProject.title}
        onChange={(e) =>
          setNewProject({ ...newProject, title: e.target.value })
        }
        required={true}
      />
      <Form.Input
        disabled={!allowedToPost}
        label="Summary:"
        name="summary"
        placeholder="Summary of project..."
        value={newProject.summary}
        onChange={(e) =>
          setNewProject({ ...newProject, summary: e.target.value })
        }
        required={true}
      />
      <Form.Field required={true} disabled={!allowedToPost}>
        <ReactQuill
          theme="snow"
          modules={modules}
          name="content"
          placeholder="Project proposal"
          value={newProject.content}
          onChange={(e) => setNewProject({ ...newProject, content: e })}
          required
        />
      </Form.Field>
      <Button
        type="submit"
        content={
          getAccountType() === accountType.citizen
            ? "Submit for proposal"
            : "Publish to active"
        }
        disabled={
          newProject.summary === emptyForms.newProject.summary ||
          newProject.content === emptyForms.newProject.content ||
          newProject.title === emptyForms.newProject.title ||
          !allowedToPost
        }
      />
    </Form>
  );
}

export default ProjectForm;
