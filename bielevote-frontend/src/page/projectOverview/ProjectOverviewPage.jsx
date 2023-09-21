import { useState, useEffect } from "react";
import {
  Button,
  List,
  Pagination,
  Header as SemanticHeader,
} from "semantic-ui-react";

import { Header } from "../../components";
import ListProjects from "./components/ListProjects";
import { backendApi } from "../../misc/ApiMappings";
import { emptyForms, projectStatus } from "../../misc/ApiForms";

export default function ProjectOverviewPage() {
  const [projectsList, setProjectsList] = useState(emptyForms.projectOverview);
  const [viewActive, setViewActive] = useState(true);
  const [viewAccepted, setViewAccepted] = useState(true);
  const [viewRejected, setViewRejected] = useState(false);

  useEffect(() => {
    handlePageChange();
  }, [viewActive, viewAccepted, viewRejected]);

  const handlePageChange = async (event, value) => {
    let page;
    if (value) {
      page = value.activePage - 1;
    } else {
      page = 0;
    }
    let statusFilter = [];
    if (viewActive) statusFilter.push(projectStatus.active);
    if (viewAccepted) statusFilter.push(projectStatus.accepted);
    if (viewRejected) statusFilter.push(projectStatus.rejected);
    console.log(statusFilter);
    const response = await backendApi.getAllProjects(page, 3, statusFilter);
    setProjectsList(response.data);
  };

  return (
    <div className="flex flex-col gap-5 w-screen">
      <Header pageTitle="Projects" />
      <div className="grid grid-cols-5 items-start">
        <List>
          <SemanticHeader as="h3">Filter by status:</SemanticHeader>
          <Button.Group vertical>
            <Button
              toggle
              active={viewActive}
              onClick={() => setViewActive((state) => (state = !state))}
              content="Active"
            />
            <Button
              toggle
              active={viewAccepted}
              onClick={() => setViewAccepted((state) => (state = !state))}
              content="Accepted"
            />
            <Button
              toggle
              active={viewRejected}
              onClick={() => setViewRejected((state) => (state = !state))}
              content="Rejected"
            />
          </Button.Group>
        </List>
        <div className="col-span-3 flex flex-col items-center w-full">
          <ListProjects projectsList={projectsList} />
          <Pagination
            defaultActivePage={1}
            totalPages={projectsList.totalPages}
            onPageChange={handlePageChange}
          />
        </div>
      </div>
    </div>
  );
}
