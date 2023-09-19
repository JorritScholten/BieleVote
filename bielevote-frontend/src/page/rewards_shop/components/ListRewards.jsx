import { GiAcorn } from "react-icons/gi";

import PropTypes from "prop-types";
import Reward from "./Reward";
import { Grid } from "semantic-ui-react";

export default function ListRewards({ rewardsList }) {
  return rewardsList.rewards == [] ? (
    <div>loading...</div>
  ) : (
    <div className="w-3/5 m-20">
      <Grid container columns={3}>
        {rewardsList.rewards.map((rewardPreview) => (
          <Grid.Column key={rewardPreview.id}>
            <div className="p-3 flex flex-col">
              <div className="text-xl text-black-700 font-bold">
                {rewardPreview.name}
              </div>
              <div className="flex flex-row items-center">
                <div className="text-lg mr-1 text-gray-600">
                  {rewardPreview.cost}
                </div>
                <div className="flex mr-4">
                  <GiAcorn />
                </div>
                <Reward rewardId={rewardPreview.id} />
              </div>
            </div>
          </Grid.Column>
        ))}
      </Grid>
    </div>
  );
}

ListRewards.propTypes = {
  rewardsList: PropTypes.object.isRequired,
};