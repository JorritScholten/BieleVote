import React from "react";
import Header from "../../components/Header";
import ProjectForm from "./components/ProjectForm";
import { Button } from "semantic-ui-react";
import { useEffect } from "react";

function ProjectWritingPage() {
  return (
    <>
      <Header pageTitle="Project" />

      <div className="flex flex-col px-20 py-10">
        <h1>Propose something for the city</h1>
        
        <ProjectForm />
      </div>

    </>
  );
}

export default ProjectWritingPage;