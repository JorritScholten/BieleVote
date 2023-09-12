import { useState } from "react";
import { backendApi, handleLogError } from "../misc/ApiMappings";
import { useAuth } from "../misc/AuthContext";

export default function PrintUserInfo() {
  const Auth = useAuth();
  const [user, setUser] = useState(null);

  async function fetchData() {
    try {
      console.log("user: " + Auth.getUsername());
      const response = await backendApi.userInfo(Auth.getUser());
      console.log(response);
      setUser(response.data);
      console.log(response.data);
    } catch (error) {
      handleLogError(error);
    }
  }

  return (
    <div className="bg-slate-200 w-fit h-fit flex flex-col gap-2 p-2">
      <button className="bg-green-300 p-1" onClick={fetchData}>
        get user info
      </button>
      <div className="p-1">username: {JSON.stringify(user)}</div>
    </div>
  );
}
