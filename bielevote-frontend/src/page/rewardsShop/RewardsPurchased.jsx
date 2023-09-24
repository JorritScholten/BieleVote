import { useEffect, useState } from "react";
import { backendApi, handleLogError } from "../../misc/ApiMappings";
import { useAuth } from "../../misc/AuthContext";

export default function RewardsPurchased() {
  const { getUser, userIsAuthenticated } = useAuth();
  const [purchases, setPurchases] = useState();
  useEffect(() => {
    async function getPurchases() {
      try {
        const response = backendApi.getAccountBalance(getUser());
        setPurchases(response.data);
        console.log(purchases);
      } catch (error) {
        handleLogError(error);
      }
    }
  });
  return <div></div>;
}
