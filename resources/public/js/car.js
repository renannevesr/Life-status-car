function deleteCar(carId) {
  fetch(`/delete-car/${carId}`, {
    method: "DELETE",
  })
    .then((response) => {
      if (response.ok) {
        window.location.reload();
      } else {
        console.log("Erro ao excluir o carro");
      }
    })
    .catch((error) => {
      console.error("Erro ao excluir o carro:", error);
    });
}

function editCar(carId) {
  const modeloInput = document.getElementById("model");
  const montadoraInput = document.getElementById("brand");
  const anoInput = document.getElementById("year");

  const updatedCar = {
    model: modeloInput.value,
    brand: montadoraInput.value,
    year: anoInput.value,
  };
  console.log(updatedCar);
  console.log(carId);
  fetch(`/edit-car/${carId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(updatedCar),
    //body: updatedCar,
  })
    .then((response) => {
      if (response.ok) {
        window.location.reload();
      } else {
        console.log("Erro ao atualizar o carro");
      }
    })
    .catch((error) => {
      console.error("Erro ao atualizar o carro:", error);
    });
}
