import { useEffect, useState } from "react";
import { Button, Header, Image, Modal } from "semantic-ui-react";
import PropTypes from "prop-types";
import { emptyForms } from "../../../misc/ApiForms";
import { backendApi } from "../../../misc/ApiMappings";
export default function Reward({ rewardId }) {
  const [open, setOpen] = useState(false);
  const [reward, setRewardItem] = useState(emptyForms.rewardItem);

  useEffect(() => {
    fetchReward(rewardId);
  }, [rewardId]);

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
      trigger={<Button>More Info</Button>}
    >
      <Modal.Header>{reward.name}</Modal.Header>
      <Modal.Content image>
        <Image
          size="medium"
          src="https://react.semantic-ui.com/images/avatar/large/rachel.png"
          wrapped
        />
        <Modal.Description>
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
