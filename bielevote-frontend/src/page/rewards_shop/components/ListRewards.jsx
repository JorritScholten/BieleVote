import { GiAcorn } from "react-icons/gi";

import PropTypes from "prop-types";
import Reward from "./Reward";

export default function ListRewards({ rewardsList }) {
  return rewardsList.rewards == [] ? (
    <div>loading...</div>
  ) : (
    <div className="flex flex-col  w-3/5">
      {rewardsList.rewards.map((rewardPreview) => (
        <div className="p-3 flex flex-col" key={rewardPreview.id}>
          <div className="text-xl text-black-700 font-bold">
            {rewardPreview.name}
          </div>
          <div className="flex flex-row">
            <div className="text-lg mr-1 text-gray-600">
              {rewardPreview.cost}
            </div>
            <div className="flex items-center">
              <GiAcorn />
            </div>
            <Reward rewardId={rewardPreview.id} />
          </div>
        </div>
      ))}
    </div>
  );
}

ListRewards.propTypes = {
  rewardsList: PropTypes.object.isRequired,
};
