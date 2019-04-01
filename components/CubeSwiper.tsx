import React, {Component} from "react";
import {requireNativeComponent, View, Text, Dimensions, Alert} from "react-native";

const CubeRotateSwiper = requireNativeComponent('CubeRotateSwiper');
const {width} = Dimensions.get('window');

interface Props {
    startPage?: number;
    onPageChange?: (page: number) => void;
}

export default class CubeSwiper extends Component<Props> {
    _onPageChange = (event: any) => {
        if (this.props.onPageChange != null) {
            this.props.onPageChange(event.nativeEvent.page);
        }
    };

    render() {
        return (
            <CubeRotateSwiper style={{flex: 1, width}} onPageSelected={this._onPageChange} startPage={this.props.startPage}>
                {this.props.children}
            </CubeRotateSwiper>
        );
    }
}