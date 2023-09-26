import { useEffect, useState } from "react";
import { backendApi, handleLogError } from "../../misc/ApiMappings";
import { useAuth } from "../../misc/AuthContext";
import { Header } from "../../components";
import { emptyForms } from "../../misc/ApiForms";
import ListRewardsPurchased from "./components/ListRewardsPurchased";
import { Pagination } from "semantic-ui-react";

export default function RewardsPurchased() {
  const { getUser, userIsAuthenticated } = useAuth();
  const [transactionList, setTransactionList] = useState(
    emptyForms.rewardTransactions
  );

  useEffect(() => {
    handlePageChange();
  }, [getUser, userIsAuthenticated]);
  const handlePageChange = async (event, value) => {
    let page;
    if (value) {
      page = value.activePage - 1;
    } else {
      page = 0;
    }
    try {
      const response = await backendApi.getRewardTransactions(
        getUser(),
        page,
        9
      );
      setTransactionList(response.data);
    } catch (error) {
      handleLogError(error);
    }
  };

  return (
    <div>
      <Header pageTitle="Rewards purchased" />
      <div className="flex flex-row items-center justify-center">
        <ListRewardsPurchased transactionList={transactionList} />
      </div>
      <div className="flex flex-row items-center justify-center">
        <Pagination
          defaultActivePage={1}
          totalPages={transactionList.totalPages}
          onPageChange={handlePageChange}
        />
      </div>
    </div>
  );
}
