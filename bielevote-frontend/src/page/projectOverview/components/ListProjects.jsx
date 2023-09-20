import PropTypes from "prop-types";
import { Link } from "react-router-dom";

import { formatDate } from "../../../components/Utils";

export default function ListProjects({ projectsList }) {
  return projectsList.projects == [] ? (
    <div>loading...</div>
  ) : (
    <>
      <div className="flex flex-col  w-3/5">
        {projectsList.projects.map((project) => (
          <div className="p-3 flex flex-col" key={project.id}>
            <div>{project.id}</div>
            <Link to={"/projects/" + project.id}>
              <div className="text-3xl text-blue-700 font-bold underline">
                {project.title}
              </div>
            </Link>
            <div>Author: {project.author.legalName}</div>
            <div>Published on: {formatDate(project.datePublished)}</div>
            <div>{project.summary}</div>
            <div>Status: {project.status}</div>
          </div>
        ))}
      </div>
    </>
  );
}

ListProjects.propTypes = {
  projectsList: PropTypes.object.isRequired,
};
