import { useEffect, useState } from "react";
import { Button, Header, Modal } from "semantic-ui-react";
import PropTypes from "prop-types";
import { GiAcorn } from "react-icons/gi";

import { emptyForms } from "../../../misc/ApiForms";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";
import { useAuth } from "../../../misc/AuthContext";
import { HttpStatusCode } from "axios";

export default function Reward({ rewardId }) {
  const { getUser } = useAuth();
  const [open, setOpen] = useState(false);
  const [reward, setRewardItem] = useState(emptyForms.rewardItem);
  const [newTransaction, setNewTransaction] = useState(
    emptyForms.rewardTransactionDto
  );

  useEffect(() => {
    fetchReward(rewardId);
  }, [rewardId]);

  async function makePurchase() {
    const transaction = { ...newTransaction, rewardId: rewardId };
    setNewTransaction(transaction);
    try {
      const response = await backendApi.postRewardTransaction(
        getUser(),
        transaction
      );
      setOpen(false);
      if (response.status === HttpStatusCode.Created) {
        setNewTransaction(emptyForms.rewardTransactionDto);
      }
    } catch (error) {
      handleLogError(error);
    }
  }

  async function fetchReward(rewardId) {
    try {
      const response = await backendApi.getRewardById(rewardId);
      setRewardItem(response.data);
    } catch (error) {
      console.log(error);
    }
  }
  return (
    <Modal
      onClose={() => setOpen(false)}
      onOpen={() => setOpen(true)}
      open={open}
      trigger={<Button>More info / Buy</Button>}
    >
      <Modal.Header>{reward.name}</Modal.Header>
      <Modal.Content image>
        <Modal.Description>
          <div className="flex flex-row items-center">
            Cost: {reward.cost}
            <div className="ml-1">
              <GiAcorn />
            </div>
          </div>
          <Header>Description</Header>
          <div>{reward.description}</div>
        </Modal.Description>
      </Modal.Content>
      <Modal.Actions>
        <Button color="black" onClick={() => setOpen(false)}>
          Nope
        </Button>
        <Button
          content="Purchase"
          labelPosition="right"
          icon="checkmark"
          onClick={() => setOpen(false)}
          positive
        />
      </Modal.Actions>
    </Modal>
  );
}
Reward.propTypes = {
  rewardId: PropTypes.number.isRequired,
};
