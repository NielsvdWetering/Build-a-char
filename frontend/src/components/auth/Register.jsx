import { useState } from "react";
import AuthForm from "./AuthForm";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useAuthentication } from "../../hooks";

export default function Register() {
  const [registerData, setRegisterData] = useState({
    username: "",
    password: "",
  });
  const navigate = useNavigate();
  const { register } = useAuthentication();

  return (
    <AuthForm
      username={registerData.username}
      setUsername={(newUsername) =>
        setRegisterData((registerData) => ({
          ...registerData,
          username: newUsername ?? "",
        }))
      }
      password={registerData.password}
      setPassword={(newPassword) =>
        setRegisterData((registerData) => ({
          ...registerData,
          password: newPassword ?? "",
        }))
      }
      onSubmit={handleSubmit}
      submitTitle="Register"
      redirectTitle="or login if your already have an account"
      redirectURL="/login"
    />
  );

  function handleSubmit() {
    console.log(registerData);
    register(registerData)
      .then((_) => navigate("/"))
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
