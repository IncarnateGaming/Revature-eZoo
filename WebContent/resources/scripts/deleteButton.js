/**
 * 
 */
class DeleteButton{
    static setup(){
        const deleteButtons = document.getElementsByClassName('delete-button');
        [].forEach.call(deleteButtons, button =>{
            button.addEventListener('click',DeleteButton.delete);
        });
    }
    static delete(ev){
        if(confirm(`Are you sure you want to delete: ${ev.target.getAttribute('data-label')}`)){
            window.location.href= ev.target.getAttribute('data-delete');
        }
    }
}
DeleteButton.setup();
