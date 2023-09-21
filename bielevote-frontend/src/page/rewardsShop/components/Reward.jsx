import { useEffect, useState } from "react";
import { Button, ButtonGroup, Header, Modal } from "semantic-ui-react";
import PropTypes from "prop-types";
import { BiBug } from "react-icons/bi";

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

  const [count, setCount] = useState(1);
  const [canPurchase, setCanPurchase] = useState(false);

  useEffect(() => {
    fetchReward(rewardId);
  }, [rewardId]);

  async function makePurchase() {
    const transaction = {
      ...newTransaction,
      rewardId: rewardId,
      rewardsAmount: count,
    };
    setNewTransaction(transaction);
    try {
      const response = await backendApi.postRewardTransaction(
        getUser(),
        transaction
      );
      setOpen(false);
      if (response.status === HttpStatusCode.Created) {
        setNewTransaction(emptyForms.rewardTransactionDto);
        alert("Thank you for your purchase!");
      }
    } catch (error) {
      handleLogError(error);
      alert("Something went wrong... Please try again");
    }
  }

  async function fetchReward(rewardId) {
    try {
      const response = await backendApi.getRewardById(rewardId);
      setRewardItem(response.data);
    } catch (error) {
      handleLogError(error);
    }
  }

  const changeCount = (increase) => {
    if (count + increase > 0) setCount(count + increase);
  };

  return (
    <Modal
      onClose={() => setOpen(false)}
      onOpen={() => setOpen(true)}
      open={open}
      trigger={
        <Button.Group>
          <Button>More info</Button>
          <Button positive>Purchase</Button>
        </Button.Group>
      }
    >
      <Modal.Header>{reward.name}</Modal.Header>
      <Modal.Content image>
        <Modal.Description>
          <div className="flex flex-row items-center">
            Cost: {reward.cost}
            <div className="ml-1">
              <BiBug />
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
        <Button.Group>
          <Button onClick={() => changeCount(-1)}>-</Button>
          <Button onClick={() => changeCount(1)}>{count}</Button>
          <Button onClick={() => changeCount(1)}>+</Button>
          <Button
            content="Purchase"
            labelPosition="right"
            icon="checkmark"
            onClick={() => makePurchase()}
            positive
          />
        </Button.Group>
      </Modal.Actions>
    </Modal>
  );
}

Reward.propTypes = {
  rewardId: PropTypes.number.isRequired,
};
