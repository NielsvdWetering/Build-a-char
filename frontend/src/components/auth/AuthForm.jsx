import InputField from "../generic/InputField";
import { useState } from "react";

export default function AuthForm({
  onSubmit,
  submitText,
  onRedirect,
  redirectText,
  onCompleted,
}) {
  const [authData, setAuthData] = useState({ username: "", password: "" });

  return (
    <div className="mt-20 flex flex-col items-center gap-5">
      <InputField
        className="w-1/4"
        value={authData.username}
        onChange={(event) =>
          setAuthData((data) => ({ ...data, username: event.target.value }))
        }
        placeholder="username"
        onKeyUp={onInputFieldKeyUp}
      />
      <InputField
        className="w-1/4"
        value={authData.password}
        onChange={(event) =>
          setAuthData((data) => ({ ...data, password: event.target.value }))
        }
        placeholder="password"
        type="password"
        onKeyUp={onInputFieldKeyUp}
      />
      <button
        className="btn btn-primary w-52"
        onClick={handleSubmit}
        disabled={
          authData.username.length === 0 || authData.password.length === 0
        }
      >
        {submitText ?? "Submit"}
      </button>
      <span
        className="cursor-pointer text-blue-800 underline hover:text-purple-600"
        onClick={handleRedirect}
      >
        {redirectText ?? "redirect"}
      </span>
    </div>
  );

  function onInputFieldKeyUp(event) {
    if (event.key === "Enter") {
      handleSubmit();
    }
  }

  function handleSubmit() {
    if (!onSubmit) {
      console.error("submit handler not found");
      return;
    }

    onSubmit(authData)
      .then(handleCompleted)
      .catch((error) =>
        alert(
          error.response.data.detail
            .split(";")
            .map((s) => `- ${s}`)
            .join("\n"),
        ),
      );
  }

  function handleRedirect() {
    if (!onRedirect) {
      console.error("redirect handler not found");
      return;
    }

    onRedirect();
  }

  function handleCompleted() {
    if (!onCompleted) {
      console.error("auth completed handler not found");
      return;
    }

    onCompleted();
  }
}
