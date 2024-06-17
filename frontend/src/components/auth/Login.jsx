import { useState } from "react";
import AuthForm from "./AuthForm";
import { useNavigate } from "react-router-dom";
import { useAuthentication } from "../../hooks";

export default function Login() {
  const [loginData, setLoginData] = useState({ username: "", password: "" });
  const navigate = useNavigate();

  const { login } = useAuthentication();

  return (
    <AuthForm
      onSubmit={handleSubmit}
      submitTitle="Login"
      username={loginData.username}
      setUsername={(newUsername) =>
        setLoginData((loginData) => ({
          ...loginData,
          username: newUsername ?? "",
        }))
      }
      password={loginData.password}
      setPassword={(newPassword) =>
        setLoginData((loginData) => ({
          ...loginData,
          password: newPassword ?? "",
        }))
      }
      redirectTitle="or register if you do not yet have an account"
      redirectURL="/register"
    />
  );

  function handleSubmit() {
    login(loginData)
      .then((_) => {
        navigate("/");
      })
      .catch((error) =>
        alert(
          error.response.data.detail
            .split(";")
            .map((s) => `- ${s}`)
            .join("\n"),
        ),
      );
  }
}
