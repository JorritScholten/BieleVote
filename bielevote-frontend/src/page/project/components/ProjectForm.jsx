import React from "react";
import { useState } from "react";
import { Form, Button } from "semantic-ui-react";

function ProjectForm() {
  const [projectData, setProjectData] = useState(projectForm.emptyForm);

  const projectForm = {
    emptyForm: {
      title: "",
      content: "",
    },
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setProjectData(projectForm.emptyForm);
  };

  const handleFormChange

  return (
    <div>
      <Form onSubmit={handleSubmit}>
        <Form.Input
          name="title"
          placeholder="title"
          value={projectData.title}
          onChange={handleFormChange}
          pattern="[\S]+"
          required={true}
        />
        <Form.Input
          name="content"
          placeholder="content"
          value={FormData.content}
          onChange={handleFormChange}
          pattern="[\S]+"
          required={true}
        />
      </Form>
    </div>
  );
}

export default ProjectForm;
