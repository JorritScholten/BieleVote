import { useState } from "react";
import { Button, Form, Header, Modal } from "semantic-ui-react";
import { emptyForms } from "../../../misc/ApiForms";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";
import { useAuth } from "../../../misc/AuthContext";
import { HttpStatusCode } from "axios";

export default function CreateReward() {
  const [open, setOpen] = useState(false);
  const [newReward, setNewReward] = useState(emptyForms.createRewardDto);
  const { getUser } = useAuth();

  async function onSubmit(e) {
    e.preventDefault();
    try {
      const response = await backendApi.postReward(getUser(), newReward);
      if (response.status === HttpStatusCode.Created) {
        setNewReward(emptyForms.createRewardDto);
        setOpen(false);
      }
    } catch (error) {
      handleLogError(error);
    }
  }

  return (
    <div>
      <Modal
        onClose={() => setOpen(false)}
        onOpen={() => setOpen(true)}
        open={open}
        trigger={<Button positive>Purchase</Button>}
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
              required={true}
            />
          </Modal.Header>
          <Modal.Content image>
            <Modal.Description>
              <Form.Input
                label="Cost:"
                name="cost"
                placeholder="Cost"
                value={newReward.cost}
                onChange={(e) =>
                  setNewReward({ ...newReward, cost: e.target.value })
                }
                required={true}
              />
              <Header>Description</Header>
              <div>
                <Form.Input
                  label="Description:"
                  name="description"
                  placeholder="Description"
                  value={newReward.description}
                  onChange={(e) =>
                    setNewReward({ ...newReward, description: e.target.value })
                  }
                  required={true}
                />
              </div>
            </Modal.Description>
          </Modal.Content>
          <Modal.Actions>
            <Button color="black" onClick={() => setOpen(false)}>
              Nope
            </Button>
            <Button
              type="submit"
              content="Submit"
              labelPosition="right"
              positive
              active={
                !(
                  newReward.name === emptyForms.createRewardDto.name ||
                  newReward.cost === emptyForms.createRewardDto.cost
                )
              }
            />
          </Modal.Actions>
        </Form>
      </Modal>
    </div>
  );
}
