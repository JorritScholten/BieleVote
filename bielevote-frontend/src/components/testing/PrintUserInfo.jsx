import { useState } from "react";
import { backendApi, handleLogError } from "../../misc/ApiMappings";
import { useAuth } from "../../misc/AuthContext";

export default function PrintUserInfo() {
  const Auth = useAuth();
  const { userLogout } = useAuth();
  const [user, setUser] = useState(null);

  function logout() {
    userLogout();
    setUser(null);
  }

  async function fetchData() {
    try {
      const response = await backendApi.userInfo(Auth.getUser());
      setUser(response.data);
    } catch (error) {
      handleLogError(error);
    }
  }

  return (
    <div className="bg-slate-200 w-fit h-fit flex flex-col gap-2 p-2">
      <button className="bg-red-300 p-1" onClick={logout}>
        logout
      </button>
      <button className="bg-green-300 p-1" onClick={fetchData}>
        get user info
      </button>
      <div className="p-1">
        info: <pre>{JSON.stringify(user, null, 2)}</pre>
      </div>
    </div>
  );
}
