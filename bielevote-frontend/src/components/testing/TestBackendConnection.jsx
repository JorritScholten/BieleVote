import { useState } from "react";
import { backendApi } from "../misc/ApiMappings";

export default function TestBackendConnection() {
  const [authResponse, setAuthResponse] = useState("Untested");
  async function authTest() {
    try {
      const res = await backendApi.authTest();
      setAuthResponse(res.data === "Hello world" ? "Success" : "Failure");
    } catch (error) {
      setAuthResponse("Failure");
    }
  }

  return (
    <div className="bg-slate-200 w-fit h-fit flex flex-col gap-2 p-2">
      <button className="bg-green-300 p-1" onClick={authTest}>
        test connection
      </button>
      <div className="p-1">test result: {authResponse}</div>
    </div>
  );
}
