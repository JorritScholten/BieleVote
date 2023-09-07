import React from "react";
import Header from "./Header";

function ProjectWritingPage() {
  return (
    <>
      <Header pageTitle="Project" />

      <div className="flex flex-col px-20 py-10">
        <h1>Propose something for the city</h1>
        <form className="flex flex-col">
          <label for="title">Title</label>
          <input type="text"></input>
          <label for="content">Content</label>
          <input type="text"></input>
          <input
            type="submit"
            value="Send"
            className="rounded-lg border-2 border-black"
          ></input>
        </form>
      </div>
    </>
  );
}

export default ProjectWritingPage;
