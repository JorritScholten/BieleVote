import { useState, useEffect } from "react";
import {
  Button,
  List,
  Pagination,
  Header as SemanticHeader,
} from "semantic-ui-react";

import { Header } from "../../components";
import ListProjects from "./components/ListProjects";
import { backendApi, handleLogError } from "../../misc/ApiMappings";
import { emptyForms, projectStatus } from "../../misc/ApiForms";
import { useAuth } from "../../misc/AuthContext";

export default function OwnProjectOverviewPage() {
  const [projectsList, setProjectsList] = useState(emptyForms.projectOverview);
  const [viewActive, setViewActive] = useState(true);
  const [viewAccepted, setViewAccepted] = useState(true);
  const [viewRejected, setViewRejected] = useState(true);
  const [viewProposed, setViewProposed] = useState(true);
  const [viewDenied, setViewDenied] = useState(true);
  const { getUser } = useAuth();
  const amountOfProjects = 3;

  useEffect(() => {
    handlePageChange();
  }, [viewActive, viewAccepted, viewRejected, viewProposed, viewDenied]);

  const handlePageChange = async (event, value) => {
    const page = value != null ? value.activePage - 1 : 0;
    let statusFilter = [];
    if (viewActive) statusFilter.push(projectStatus.active);
    if (viewAccepted) statusFilter.push(projectStatus.accepted);
    if (viewRejected) statusFilter.push(projectStatus.rejected);
    if (viewProposed) statusFilter.push(projectStatus.proposed);
    if (viewDenied) statusFilter.push(projectStatus.denied);
    try {
      const response = await backendApi.getAllOwnProjects(
        page,
        amountOfProjects,
        statusFilter,
        getUser()
      );
      setProjectsList(response.data);
    } catch (error) {
      handleLogError(error);
    }
  };

  return (
    <div className="flex flex-col gap-5 w-screen">
      <Header pageTitle="My projects" />
      <div className="grid grid-cols-5 items-start justify-items-center">
        <List>
          <SemanticHeader as="h3">Filter by status:</SemanticHeader>
          <Button.Group vertical fluid>
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
            <Button
              toggle
              active={viewProposed}
              onClick={() => setViewProposed((state) => (state = !state))}
              content="Proposed"
            />
            <Button
              toggle
              active={viewDenied}
              onClick={() => setViewDenied((state) => (state = !state))}
              content="Denied"
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
