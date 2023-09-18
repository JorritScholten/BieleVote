import { GiAcorn } from "react-icons/gi";

import PropTypes from "prop-types";

export default function ListRewards({ rewardsList }) {
  return rewardsList.rewards == [] ? (
    <div>loading...</div>
  ) : (
    <div className="flex flex-col  w-3/5">
      {rewardsList.rewards.map((reward) => (
        <div className="p-3 flex flex-col" key={reward.id}>
          <div className="text-3xl text-blue-700 font-bold underline">
            {reward.name}
          </div>
          <div className="flex flex-row">
            <div className="text-lg mr-1 text-gray-600">{reward.cost}</div>
            <div className="flex items-center">
              <GiAcorn />
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}

ListRewards.propTypes = {
  rewardsList: PropTypes.object.isRequired,
};
