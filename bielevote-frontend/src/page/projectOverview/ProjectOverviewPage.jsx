import { useState, useEffect } from "react";
import { Pagination } from "semantic-ui-react";

import { Header } from "../../components";
import ListProjects from "./components/ListProjects";
import { backendApi } from "../../misc/ApiMappings";
import { emptyForms } from "../../misc/ApiForms";

export default function ProjectOverviewPage() {
  const [projectsList, setProjectsList] = useState(emptyForms.projectOverview);

  useEffect(() => {
    handlePageChange();
  }, []);

  const handlePageChange = async (event, value) => {
    let page;
    if (value) {
      page = value.activePage - 1;
    } else {
      page = 0;
    }
    const response = await backendApi.getAllProjects(page, 3);
    setProjectsList(response.data);
  };

  return (
    <>
      <Header pageTitle="Projects" />
      <div className="flex flex-col items-center justify-center">
        <ListProjects projectsList={projectsList} />
      </div>
      <div className="flex flex-col items-center justify-center">
        <Pagination
          defaultActivePage={1}
          totalPages={projectsList.totalPages}
          onPageChange={handlePageChange}
        />
      </div>
    </>
  );
}
