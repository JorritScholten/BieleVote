import { useEffect, useState } from "react";
import { emptyForms } from "../../../misc/ApiForms";
import { useAuth } from "../../../misc/AuthContext";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";
import { Header } from "../../../components";
import ListAccountRequests from "./ListAccountRequests";
import { Pagination } from "semantic-ui-react";

export default function AccountRequests() {
  const [accountRequestList, setAccountRequestList] = useState(
    emptyForms.newAccountRequests
  );
  const { getUser, userIsAuthenticated } = useAuth();
  const [version, setVersion] = useState(0);

  useEffect(() => {
    handlePageChange();
  }, [version, userIsAuthenticated]);

  const handlePageChange = async (event, value) => {
    let page;
    if (value) {
      page = value.activePage - 1;
    } else {
      page = 0;
    }
    try {
      const response = await backendApi.getAllAccountRequests(
        page,
        10,
        getUser()
      );
      setAccountRequestList(response.data);
    } catch (error) {
      handleLogError(error);
    }
  };

  return (
    <div>
      <Header pageTitle="Requested Accounts" />
      <div className="flex flex-row items-center justify-center">
        <ListAccountRequests accountRequestList={accountRequestList} />
      </div>
      <div className="flex flex-row items-center justify-center">
        <Pagination
          defaultActivePage={1}
          totalPages={accountRequestList.totalPages}
          onPageChange={handlePageChange}
        />
      </div>
    </div>
  );
}
