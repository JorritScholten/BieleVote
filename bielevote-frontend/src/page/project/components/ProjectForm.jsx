import { useState } from "react";
import { Form, Button } from "semantic-ui-react";
import { emptyForms } from "../../../misc/ApiForms";
import { useAuth } from "../../../misc/AuthContext";
import { backendApi } from "../../../misc/ApiMappings";

function ProjectForm() {
  const [newProject, setNewProject] = useState(emptyForms.newProject);
  const { getUser } = useAuth();

  async function onSubmit(e) {
    e.preventDefault();
    await backendApi.postProject(getUser(), newProject).then(() => {
      setNewProject(emptyForms.newProject);
    });
  }

  return (
    <div>
      <Form onSubmit={onSubmit}>
        <Form.Input
          label="Title:"
          width={10}
          name="title"
          placeholder="title"
          value={newProject.title}
          onChange={(e) =>
            setNewProject({ ...newProject, title: e.target.value })
          }
          required={true}
        />
        <Form.Input
          label="Content:"
          width={10}
          name="content"
          placeholder="content"
          value={newProject.content}
          onChange={(e) =>
            setNewProject({ ...newProject, content: e.target.value })
          }
          required={true}
        />
        <Button type="submit" className="rounded-lg border-2 border-black">
          Send
        </Button>
      </Form>
    </div>
  );
}

export default ProjectForm;
