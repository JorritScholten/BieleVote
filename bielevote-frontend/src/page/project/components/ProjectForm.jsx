import { useState } from "react";
import { Form, Button } from "semantic-ui-react";
import PropTypes from "prop-types";
import { emptyForms } from "../../../misc/ApiForms";

function ProjectForm({ incrementDataVersion }) {
  const [newProject, setNewProject] = useState(emptyForms.newProject);

  function onSubmit(e) {
    e.preventDefault();
    fetch(`http://localhost:8080/api/v1/project`, {
      method: "post",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newProject),
    }).then(() => {
      incrementDataVersion();
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

ProjectForm.propTypes = {
  incrementDataVersion: PropTypes.func,
};

export default ProjectForm;
