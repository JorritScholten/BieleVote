import { useState } from "react";
import { Form, Button } from "semantic-ui-react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";
import { HttpStatusCode } from "axios";

import { emptyForms } from "../../../misc/ApiForms";
import { useAuth } from "../../../misc/AuthContext";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";

function ProjectForm() {
  const [newProject, setNewProject] = useState(emptyForms.newProject);
  const { getUser } = useAuth();

  async function onSubmit(e) {
    e.preventDefault();
    try {
      const response = await backendApi.postProject(getUser(), newProject);
      if (response.status === HttpStatusCode.Created) {
        setNewProject(emptyForms.newProject);
      }
    } catch (error) {
      handleLogError(error);
    }
  }

  return (
    <Form onSubmit={onSubmit}>
      <Form.Input
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
        label="Summary:"
        name="summary"
        placeholder="Summary of project..."
        value={newProject.summary}
        onChange={(e) =>
          setNewProject({ ...newProject, summary: e.target.value })
        }
        required={true}
      />
      <Form.Field required={true}>
        <ReactQuill
          theme="snow"
          name="content"
          placeholder="Project proposal"
          value={newProject.content}
          onChange={(e) => setNewProject({ ...newProject, content: e })}
          required
        />
      </Form.Field>
      <Button
        type="submit"
        content="Submit"
        active={
          !(
            newProject.content === emptyForms.newProject.content ||
            newProject.title === emptyForms.newProject.title
          )
        }
      />
    </Form>
  );
}

export default ProjectForm;
