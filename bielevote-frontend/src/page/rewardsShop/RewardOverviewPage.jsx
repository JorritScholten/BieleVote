import { useState, useEffect } from "react";
import { Pagination } from "semantic-ui-react";

import Header from "../../components/Header";
import { backendApi, handleLogError } from "../../misc/ApiMappings";
import ListRewards from "./components/ListRewards";
import { emptyForms } from "../../misc/ApiForms";
import { useAuth } from "../../misc/AuthContext";
import { accountType } from "../../misc/NavMappings";
import CreateReward from "./components/CreateReward";

export default function RewardOverviewPage() {
  const [rewardsList, setRewardsList] = useState(emptyForms.rewardOverview);
  const { getAccountType } = useAuth();
  const [version, setVersion] = useState(0);

  useEffect(() => {
    handlePageChange();
  }, [version]);

  const handlePageChange = async (event, value) => {
    let page;
    if (value) {
      page = value.activePage - 1;
    } else {
      page = 0;
    }
    try {
      const response = await backendApi.getAllRewards(page, 9);
      setRewardsList(response.data);
    } catch (error) {
      handleLogError(error);
    }
  };

  return (
    <div>
      <Header pageTitle="Rewards Shop" />
      <div className="flex flex-row items-center justify-center">
        <ListRewards rewardsList={rewardsList} />
      </div>
      <div className="flex flex-row items-center justify-center">
        <Pagination
          defaultActivePage={1}
          totalPages={rewardsList.totalPages}
          onPageChange={handlePageChange}
        />
      </div>
      {getAccountType() === accountType.admin ? (
        <CreateReward setVersion={setVersion} />
      ) : (
        <div hidden />
      )}
    </div>
  );
}
