import { useState, useEffect } from "react";
import { Pagination } from "semantic-ui-react";

import Header from "../../components/Header";
import { backendApi } from "../../misc/ApiMappings";
import ListRewards from "./components/ListRewards";
import { emptyForms } from "../../misc/ApiForms";

export default function RewardOverviewPage() {
  const [rewardsList, setRewardsList] = useState(emptyForms.rewardOverview);

  useEffect(() => {
    console.log(rewardsList);
    handlePageChange();
  }, []);

  const handlePageChange = async (event, value) => {
    let page;
    if (value) {
      page = value.activePage - 1;
    } else {
      page = 0;
    }
    const response = await backendApi.getAllRewards(page, 3);
    setRewardsList(response.data);
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
    </div>
  );
}
