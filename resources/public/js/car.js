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