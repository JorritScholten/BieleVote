import { useEffect, useState } from "react";
import { Button, Header, Modal } from "semantic-ui-react";
import PropTypes from "prop-types";
import { BiBug } from "react-icons/bi";

import { emptyForms } from "../../../misc/ApiForms";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";
import { useAuth } from "../../../misc/AuthContext";
import { HttpStatusCode } from "axios";

export default function Reward({ rewardId }) {
  const { getUser, userIsAuthenticated } = useAuth();
  const [open, setOpen] = useState(false);
  const [reward, setRewardItem] = useState(emptyForms.rewardItem);
  const [newTransaction, setNewTransaction] = useState(
    emptyForms.rewardTransactionDto
  );

  const [count, setCount] = useState(1);
  const [userBalance, setUserBalance] = useState(0);

  useEffect(() => {
    async function fetchReward(rewardId) {
      try {
        const response = await backendApi.getRewardById(rewardId);
        setRewardItem(response.data);
      } catch (error) {
        handleLogError(error);
      }
    }
    async function getAccountBalance() {
      try {
        const response = await backendApi.getAccountBalance(getUser());
        setUserBalance(response.data);
      } catch (error) {
        handleLogError(error);
      }
    }
    fetchReward(rewardId);
    if (userIsAuthenticated()) {
      getAccountBalance();
    } else {
      setUserBalance(0);
    }
  }, [rewardId, getUser, userIsAuthenticated, open]);

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
        setUserBalance(
          (b) => (b = b - reward.cost * transaction.rewardsAmount)
        );
        setNewTransaction(emptyForms.rewardTransactionDto);
        alert("Thank you for your purchase!");
      }
    } catch (error) {
      handleLogError(error);
      alert("Something went wrong... Please try again");
    }
  }

  const changeCount = (increase) => {
    if (count + increase > 0 && count < reward.inventory) {
      setCount(count + increase);
    }
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
            Cost: {reward.cost * count}
            <div className="ml-1">
              <BiBug />
            </div>
          </div>
          <Header>Description</Header>
          <div>{reward.description}</div>
        </Modal.Description>

        <div>In stock: {reward.inventory}</div>
      </Modal.Content>
      <Modal.Actions>
        <Button color="black" onClick={() => setOpen(false)}>
          Cancel
        </Button>
        <Button.Group>
          <Button onClick={() => changeCount(-1)}>-</Button>
          <Button onClick={() => setCount(1)}>{count}</Button>
          <Button onClick={() => changeCount(1)}>+</Button>
          <Button
            content="Purchase"
            labelPosition="right"
            icon="checkmark"
            onClick={() => makePurchase()}
            positive
            disabled={userBalance < reward.cost * count}
          />
        </Button.Group>
      </Modal.Actions>
    </Modal>
  );
}

Reward.propTypes = {
  rewardId: PropTypes.number.isRequired,
};
