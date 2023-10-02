/* eslint-disable react/prop-types */
import { useEffect, useState } from "react";

export default function TimeRemaining(props) {
  const [timeRemaining, setTimeRemaining] = useState();

  useEffect(() => {
    const getRemainingTime = () => {
      const endOfVoting = new Date(props.project.endOfVoting);
      var difference = endOfVoting - Date.now();
      return {
        days: Math.floor(difference / (1000 * 60 * 60 * 24)),
        hours: Math.floor((difference / (1000 * 60 * 60)) % 24),
        minutes: Math.floor((difference / 1000 / 60) % 60),
        seconds: Math.floor((difference / 1000) % 60),
      };
    };
    setInterval(() => setTimeRemaining(getRemainingTime()), 1000);
  }, [props.project.endOfVoting]);

  return (
    <div>
      {timeRemaining ? (
        <div>
          {timeRemaining.days} days {timeRemaining.hours} hours{" "}
          {timeRemaining.minutes} minutes {timeRemaining.seconds} seconds
        </div>
      ) : (
        <div>Loading...</div>
      )}
    </div>
  );
}
