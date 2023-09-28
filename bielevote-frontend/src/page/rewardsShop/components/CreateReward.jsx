import PropTypes from "prop-types";
import { useState } from "react";
import { Button, Form, Modal } from "semantic-ui-react";
import { emptyForms } from "../../../misc/ApiForms";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";
import { useAuth } from "../../../misc/AuthContext";
import { HttpStatusCode } from "axios";

export default function CreateReward({ setVersion }) {
  const [open, setOpen] = useState(false);
  const [newReward, setNewReward] = useState(emptyForms.createRewardDto);
  const { getUser } = useAuth();

  async function onSubmit(e) {
    e.preventDefault();
    try {
      const response = await backendApi.postReward(getUser(), newReward);
      if (response.status === HttpStatusCode.Created) {
        setVersion((v) => (v = v + 1));
        setNewReward(emptyForms.createRewardDto);
        setOpen(false);
        alert("Reward is posted!");
      }
    } catch (error) {
      handleLogError(error);
      alert("Something went wrong, please try again");
    }
  }

  return (
    <div className="w-1/4 h-1/4 ml-20">
      <Modal
        className="p-5"
        onClose={() => setOpen(false)}
        onOpen={() => setOpen(true)}
        size="fullscreen"
        open={open}
        trigger={
          <Button className="h-32" fluid positive>
            Add new Reward
          </Button>
        }
      >
        <Form onSubmit={onSubmit}>
          <Modal.Header>
            <Form.Input
              label="Name:"
              name="name"
              placeholder="Name"
              value={newReward.name}
              onChange={(e) =>
                setNewReward({ ...newReward, name: e.target.value })
              }
            />
          </Modal.Header>
          <Modal.Content>
            <Form.Input
              label="Description:"
              name="description"
              placeholder="Description"
              value={newReward.description}
              onChange={(e) =>
                setNewReward({ ...newReward, description: e.target.value })
              }
            />
            <Form.Input
              label="Cost:"
              name="cost"
              inputMode="numeric"
              placeholder="Cost"
              value={newReward.cost}
              onChange={(e) =>
                setNewReward({ ...newReward, cost: e.target.value })
              }
              pattern="\d{1,8}"
            />
            <Form.Field>
              <Button
                type="button"
                toggle
                fluid
                active={newReward.isLimited}
                onClick={() =>
                  setNewReward({
                    ...newReward,
                    isLimited: !newReward.isLimited,
                  })
                }
                content="Reward has limited inventory"
              />
            </Form.Field>
            <Form.Field>
              <Button
                type="button"
                toggle
                fluid
                active={newReward.isAvailable}
                onClick={() =>
                  setNewReward({
                    ...newReward,
                    isAvailable: !newReward.isAvailable,
                  })
                }
                content="Reward is directly available in the shop"
              />
            </Form.Field>
            <Form.Input
              label="Inventory:"
              name="inventory"
              placeholder="Inventory"
              value={newReward.inventory}
              onChange={(e) =>
                setNewReward({ ...newReward, inventory: e.target.value })
              }
            />
          </Modal.Content>
          <Modal.Actions>
            <Button.Group className="pt-4">
              <Button
                type="button"
                color="black"
                onClick={() => setOpen(false)}
              >
                Cancel
              </Button>
              <Button
                color="green"
                fluid
                type="submit"
                content="Submit"
                disabled={
                  newReward.name === emptyForms.createRewardDto.name ||
                  newReward.description ===
                    emptyForms.createRewardDto.description ||
                  newReward.cost === emptyForms.createRewardDto.cost
                }
              />
            </Button.Group>
          </Modal.Actions>
        </Form>
      </Modal>
    </div>
  );
}

CreateReward.propTypes = {
  setVersion: PropTypes.func.isRequired,
};
